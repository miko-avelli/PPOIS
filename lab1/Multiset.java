import java.util.*;

public class Multiset {
    private final List<Object> elements;

    public Multiset() {
        elements = new ArrayList<>();
    }

    public void addElement(Object element) {
        elements.add(element);
    }

    public String deleteElement(Object element) {
        if (elements.contains(element)) {
            elements.remove(element);
            return null;
        } else {
            return "No such element exists";
        }
    }

    public void addNestedMultiset(Multiset nestedMultiset) {
        elements.add(nestedMultiset);
    }

    public String removeNestedMultiset(String nestedMultisetString) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            Object element = elements.get(i);
            if (element instanceof Multiset nestedMultiset) {
                if (nestedMultiset.toString().equals(nestedMultisetString)) {
                    elements.remove(i);
                    return null;
                }
            }
        }
        return "No such nested multiset exists";
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

    public Multiset combineMultiset(Multiset otherMultiset) {
        Multiset combinedMultiset = new Multiset();

        for (Object element : elements) {
            combinedMultiset.addElement(element);
        }

        for (Object element : otherMultiset.getElements()) {
            combinedMultiset.addElement(element);
        }

        return combinedMultiset;
    }

    public Multiset intersectMultiset(Multiset otherMultiset) {
        Multiset intersectionMultiset = new Multiset();
        Map<Object, Integer> elementCounts = new HashMap<>();

        for (Object element : elements) {
            elementCounts.put(element, elementCounts.getOrDefault(element, 0) + 1);
        }

        for (Object element : otherMultiset.getElements()) {
            if (elementCounts.containsKey(element) && elementCounts.get(element) > 0) {
                intersectionMultiset.addElement(element);
                elementCounts.put(element, elementCounts.get(element) - 1);
            }
        }
        return intersectionMultiset;
    }

    public Multiset differenceMultiset(Multiset otherMultiset) {
        Multiset differenceMultiset = new Multiset();
        Map<Object, Integer> elementCounts = new HashMap<>();

        for (Object element : elements) {
            elementCounts.put(element, elementCounts.getOrDefault(element, 0) + 1);
        }

        for (Object element : otherMultiset.getElements()) {
            if (elementCounts.containsKey(element) && elementCounts.get(element) > 0) {
                elementCounts.put(element, elementCounts.get(element) - 1);
            } else {
                elementCounts.put(element, -1);
            }
        }

        for (Map.Entry<Object, Integer> entry : elementCounts.entrySet()) {
            Object element = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                differenceMultiset.addElement(element);
            }
        }

        return differenceMultiset;
    }

    public void formMultisetFromString(String input) {
        String[] tokens = input.split(",");
        for (String token : tokens) {
            if (token.equals("{}")) {
                elements.add(new Multiset());
            } else {
                elements.add(token);
            }
        }
    }

    public void formMultisetFromArray(Object[] array) {
        int i = 0;
        while (i < array.length) {
            Object element = array[i];
            if (element.equals("{")) {
                int closingBraceIndex = findClosingBraceIndex(array, i + 1);
                Object[] nestedArray = extractNestedArray(array, i + 1, closingBraceIndex);
                Multiset nestedMultiset = new Multiset();
                nestedMultiset.formMultisetFromArray(nestedArray);
                elements.add(nestedMultiset);
                i = closingBraceIndex + 1;
            } else if (element.equals("}")) {
                i++;
            } else {
                elements.add(element);
                i++;
            }
        }
    }

    private int findClosingBraceIndex(Object[] array, int startIndex) {
        int count = 1;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i].equals("{")) {
                count++;
            } else if (array[i].equals("}")) {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private Object[] extractNestedArray(Object[] array, int startIndex, int endIndex) {
        int length = endIndex - startIndex;
        Object[] nestedArray = new Object[length];
        System.arraycopy(array, startIndex, nestedArray, 0, length);
        return nestedArray;
    }

    public void iterateMultiset(Multiset multiset) {
        List<Object> elements = multiset.getElements();

        for (Object element : elements) {
            if (element instanceof Multiset) {
                System.out.println(element.toString());
            } else {
                System.out.println(element.toString());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Object element : elements) {
            if (element instanceof Multiset) {
                sb.append(element.toString());
            } else {
                sb.append(element);
            }
            sb.append(",");
        }
        sb.append("}");

        return sb.toString();
    }

}