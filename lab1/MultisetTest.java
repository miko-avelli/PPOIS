import by.tseluiko.multiset.core.Multiset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultisetTest {
    @Test
    void testAddAndDeleteElement() {
        Multiset ms = new Multiset();
        ms.addElement("a");
        ms.addElement("a");

        boolean addElement = ms.addElement("b");
        Assertions.assertTrue(addElement);

        Assertions.assertEquals(ms.toString(), "{a, a, b}");
        Assertions.assertTrue(ms.deleteElement("a"));
        Assertions.assertEquals(ms.toString(), "{a, b}");
        try {
            Assertions.assertFalse(ms.deleteElement("c"));
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Element doesn't exists", e.getMessage());
        }
    }

    @Test
    void testAndRemoveNestedMultiset() {
        Multiset nms1 = new Multiset(); // nms is nested multiset
        nms1.addElement("x");
        nms1.addElement("y");

        Multiset nms2 = new Multiset();
        nms2.addElement("p");
        nms2.addElement("q");

        Multiset ms = new Multiset();
        ms.addElement("a");

        boolean addNestedMultiset = ms.addNestedMultiset(nms1);
        Assertions.assertTrue(addNestedMultiset);

        Assertions.assertTrue(ms.removeNestedMultiset(nms1));

        try {
            Assertions.assertFalse(ms.removeNestedMultiset(nms2));
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Nested multiset doesn't exists", e.getMessage());
        }
    }

    @Test
    void testIsEmpty() {
        Multiset ms = new Multiset();

        Assertions.assertTrue(ms.isEmpty());

        ms.addElement("a");

        Assertions.assertFalse(ms.isEmpty());

        ms.deleteElement("a");

        Assertions.assertTrue(ms.isEmpty());
    }

    @Test
    void testIsBelongs() {
        Multiset nms1 = new Multiset();
        nms1.addElement("x");

        Multiset nms2 = new Multiset();
        nms2.addElement("y");

        Multiset ms = new Multiset();
        ms.addElement("a");
        ms.addNestedMultiset(nms1);

        Assertions.assertTrue(ms.isBelongs("a"));
        Assertions.assertFalse(ms.isBelongs("b"));
        Assertions.assertTrue(ms.isBelongs(nms1));
        Assertions.assertFalse(ms.isBelongs(ms.isBelongs(nms2)));
    }

    @Test
    void testGetPower() {
        Multiset nms = new Multiset();
        nms.addElement("x");
        nms.addElement("y");

        Multiset ms = new Multiset();
        ms.addElement("a");
        ms.addElement("r");
        ms.addElement("qp");
        ms.addNestedMultiset(nms);

        Assertions.assertEquals(ms.getPower(), 4);
    }

    @Test
    void testFormFromString() {
        Multiset ms = new Multiset();
        String string = "a, b, {x, y}";
        ms.formFromString(string);

        Assertions.assertEquals(ms.toString(), "{a, b, {x, y}}");
    }

    @Test
    void testFormFromArray() {
        Multiset ms = new Multiset();
        char[] array = {'a', ',', 'b', '{', 'x', ',', 'y', '}'};
        ms.formFromArray(array);

        Assertions.assertEquals(ms.toString(), "{a, b, {x, y}}");
    }

    @Test
    void testUnion() {
        Multiset ms1 = new Multiset();
        String string1 = "a,b,{x,y}";
        ms1.formFromString(string1);

        Multiset ms2 = new Multiset();
        String string2 = "x,a,{}";
        ms2.formFromString(string2);

        String union = ms1.union(ms2).toString();

        Assertions.assertEquals(union, "{a, b, {x, y}, x, a, {}}");
    }

    @Test
    void testIntersection() {
        Multiset ms1 = new Multiset();
        ms1.addElement("a");
        ms1.addElement("b");

        Multiset ms2 = new Multiset();
        ms2.addElement("a");

        String intersection = ms1.intersection(ms2).toString();

        Assertions.assertEquals(intersection, "{a}");
    }

    @Test
    void testDifference() {
        Multiset ms1 = new Multiset();
        String string1 = "a,b,{x,y}";
        ms1.formFromString(string1);

        Multiset ms2 = new Multiset();
        String string2 = "x,a,{x,y}";
        ms2.formFromString(string2);

        String difference = ms1.difference(ms2).toString();

        Assertions.assertEquals(difference, "{b}");
    }
}
