package by.tseluiko.multiset.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Multiset {
    private final List<Object> elements;

    public Multiset() {
        elements = new ArrayList<>();
    }

    public boolean addElement(Object element) {
        return elements.add(element);
    }

    public boolean deleteElement(Object element) {
        if (elements.contains(element)) {
            return elements.remove(element);
        }
        return false;
    }

    public boolean addNestedMultiset(Multiset nestedMultiset) {
        return elements.add(nestedMultiset);
    }

    public boolean removeNestedMultiset(Multiset nestedMultiset) {
        return elements.remove(nestedMultiset);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean isBelongs(Object element) {
        return elements.contains(element);
    }

    private List<Object> getElements() {
        return elements;
    }

    public int getPower() {
        return elements.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i));
            if (i < elements.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void formFromString(String input) {
        elements.clear();
        int length = input.length();
        int i = 0;
        while (i < length) {
            char c = input.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (c == '{') {
                int endIndex = findClosingBracket(input, i);
                String nestedInput = input.substring(i + 1, endIndex);
                Multiset nestedMultiset = new Multiset();
                nestedMultiset.formFromString(nestedInput);
                elements.add(nestedMultiset);
                i = endIndex + 1;
            } else {
                int endIndex = findNextComma(input, i);
                if (endIndex == -1) {
                    endIndex = length;
                }
                String element = input.substring(i, endIndex).trim();
                elements.add(element);
                i = endIndex + 1;
            }
        }
    }

    private int findClosingBracket(String input, int startIndex) {
        int length = input.length();
        int bracketCount = 1;
        int endIndex = startIndex + 1;
        while (endIndex < length && bracketCount > 0) {
            char c = input.charAt(endIndex);
            if (c == '{') {
                bracketCount++;
            } else if (c == '}') {
                bracketCount--;
            }
            endIndex++;
        }
        if (bracketCount != 0) {
            throw new IllegalArgumentException("Invalid multiset format: missing closing '}'");
        }
        return endIndex - 1;
    }

    private int findNextComma(String input, int startIndex) {
        int length = input.length();
        int index = startIndex;
        while (index < length) {
            char c = input.charAt(index);
            if (c == ',') {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void formFromArray(char[] array) {
        StringBuilder currentElement = new StringBuilder();
        Stack<Multiset> stack = new Stack<>();
        Multiset currentMultiset = this;

        for (char ch : array) {
            if (isOpeningBracket(ch)) {
                processOpeningBracket(currentElement, stack, currentMultiset);
                currentMultiset = createNestedMultiset(currentMultiset);
            } else if (isClosingBracket(ch)) {
                processClosingBracket(currentElement, stack, currentMultiset);
                currentMultiset = stack.pop();
            } else if (isSeparator(ch)) {
                processSeparator(currentElement, currentMultiset);
            } else {
                currentElement.append(ch);
            }
        }

        addLastElement(currentElement, currentMultiset);
    }

    private boolean isOpeningBracket(char ch) {
        return ch == '{';
    }

    private boolean isClosingBracket(char ch) {
        return ch == '}';
    }

    private boolean isSeparator(char ch) {
        return ch == ',';
    }

    private void processOpeningBracket(StringBuilder currentElement, Stack<Multiset> stack, Multiset currentMultiset) {
        if (!currentElement.isEmpty()) {
            currentMultiset.addElement(currentElement.toString());
            currentElement.setLength(0);
        }
        stack.push(currentMultiset);
    }

    private Multiset createNestedMultiset(Multiset currentMultiset) {
        Multiset nestedMultiset = new Multiset();
        currentMultiset.addNestedMultiset(nestedMultiset);
        return nestedMultiset;
    }

    private void processClosingBracket(StringBuilder currentElement, Stack<Multiset> stack, Multiset currentMultiset) {
        if (!currentElement.isEmpty()) {
            currentMultiset.addElement(currentElement.toString());
            currentElement.setLength(0);
        }
    }

    private void processSeparator(StringBuilder currentElement, Multiset currentMultiset) {
        if (!currentElement.isEmpty()) {
            currentMultiset.addElement(currentElement.toString());
            currentElement.setLength(0);
        }
    }

    private void addLastElement(StringBuilder currentElement, Multiset currentMultiset) {
        if (!currentElement.isEmpty()) {
            currentMultiset.addElement(currentElement.toString());
        }
    }

    public Multiset union(Multiset other) {
        Multiset result = new Multiset();

        for (Object element : this.getElements()) {
            result.addElement(element);
        }

        for (Object element : other.getElements()) {
            result.addElement(element);
        }

        return result;
    }

    public Multiset intersection(Multiset other) {
        Multiset result = new Multiset();
        for (Object element : this.elements) {
            if (other.isBelongs(element)) {
                result.addElement(element);
            }
        }
        return result;
    }

    public Multiset difference(Multiset other) {
        Multiset result = new Multiset();
        for (Object element : this.elements) {
            if (element instanceof Multiset) {
                for (Object otherElement : other.getElements()) {
                    if (otherElement instanceof Multiset) {
                        Multiset nestedResult = ((Multiset) element).difference((Multiset) otherElement);
                        if (!nestedResult.isEmpty()) {
                            result.addNestedMultiset(nestedResult);
                        }
                    }
                }
            } else if (!other.isBelongs(element)) {
                result.addElement(element);
            }
        }
        return result;
    }
}
