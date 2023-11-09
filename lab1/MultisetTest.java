import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultisetTest {
    @Test
    void AddingAndDeletingElement() {
        Multiset ms = new Multiset();
        ms.addElement("a");
        ms.addElement("b");
        ms.addElement("a");
        Assertions.assertEquals(ms.toString(), "{a,b,a,}");
        ms.deleteElement("a");
        Assertions.assertEquals(ms.toString(), "{b,a,}");
        ms.deleteElement("b");
        Assertions.assertEquals(ms.toString(), "{a,}");
        Assertions.assertEquals(ms.deleteElement("c"), "No such element exists");
    }

    @Test
    void AddingAndDeletingNestedMultiset() {
        Multiset nms1 = new Multiset();
        nms1.addElement("a");
        nms1.addElement("b");

        Multiset nms2 = new Multiset();
        nms2.addNestedMultiset(nms1);

        Multiset ms = new Multiset();
        ms.addNestedMultiset(nms1);
        ms.addNestedMultiset(nms2);

        Assertions.assertEquals(ms.toString(), "{{a,b,},{{a,b,},},}");
        ms.removeNestedMultiset("{a,b,}");
        Assertions.assertEquals(ms.toString(), "{{{a,b,},},}");
        Assertions.assertEquals(ms.removeNestedMultiset("{a,}"), "No such nested multiset exists");
    }

    @Test
    void WorkingWith_formMultisetFromString() {
        String st = "a,a,b,{},{a,b,{x,y}}";
        Multiset ms = new Multiset();
        ms.formMultisetFromString(st);
        Assertions.assertEquals(ms.toString(), "{a,a,b,{},{a,b,{x,y}},}");
    }

    @Test
    void WorkingWith_formMultisetFromArray() {
        Object[] array = {"a", "a", "b", "{", "}", "{", "a", "b", "{", "x", "y", "}", "}"};

        Multiset ms = new Multiset();
        ms.formMultisetFromArray(array);

        Assertions.assertEquals(ms.toString(), "{a,a,b,{},{a,b,{x,y,},},}");
    }

    @Test
    void EmptyMultiset() {
        Multiset ms = new Multiset();
        Assertions.assertTrue(ms.isEmpty());

        ms.addElement("a");
        Assertions.assertFalse(ms.isEmpty());
    }

    @Test
    void ElementAndMultisetBelongsToMultiset() {
        Multiset nms = new Multiset();
        nms.addElement("b");

        Assertions.assertFalse(nms.isBelongs("a"));
        Assertions.assertTrue(nms.isBelongs("b"));

        Multiset ms = new Multiset();
        ms.addNestedMultiset(nms);

        Assertions.assertTrue(ms.isBelongs(nms));
    }

    @Test
    void MultisetPower() {
        Multiset ms = new Multiset();

        Assertions.assertEquals(ms.getPower(), 0);
        ms.addElement("a");
        ms.addElement("a");
        ms.addElement("b");

        Multiset nms = new Multiset();
        nms.addElement("c");
        nms.addElement("c");
        nms.addElement("d");

        ms.addNestedMultiset(nms);
        Assertions.assertEquals(ms.getPower(), 4);
    }

    @Test
    void CombineMultiset() {
        Multiset ms1 = new Multiset();
        String st1 = "a,a,b,{a,b}";
        ms1.formMultisetFromString(st1);

        Multiset ms2 = new Multiset();
        String st2 = "a,c,{a,b},{a}";
        ms2.formMultisetFromString(st2);


        Multiset rms = new Multiset();
        String stCombine = "a,a,b,{a,b},a,c,{a,b},{a}";
        rms.formMultisetFromString(stCombine);
        stCombine = rms.toString();
        Assertions.assertEquals(stCombine, ms1.combineMultiset(ms2).toString());

    }

    @Test
    void IntersectMultiset() {
        Multiset ms1 = new Multiset();
        String st1 = "a,a,b,{a,b}";
        ms1.formMultisetFromString(st1);

        Multiset ms2 = new Multiset();
        String st2 = "a,c,{a,b},{a}";
        ms2.formMultisetFromString(st2);

        Multiset rms = new Multiset();
        String stIntersection = "a,{a,b}";
        rms.formMultisetFromString(stIntersection);

        Assertions.assertEquals(rms.toString(), ms1.intersectMultiset(ms2).toString());
    }

    @Test
    void DifferenceMultiset() {
        Multiset ms1 = new Multiset();
        String st1 = "a,a,b,{a,b}";
        ms1.formMultisetFromString(st1);

        Multiset ms2 = new Multiset();
        String st2 = "a,c,{a,b},{a}";
        ms2.formMultisetFromString(st2);

        Multiset rms = new Multiset();
        String stDifference = "a,b";
        rms.formMultisetFromString(stDifference);

        Assertions.assertEquals(rms.toString(), ms1.differenceMultiset(ms2).toString());
    }

    @Test
    void SpaceTest() {
        Multiset ms = new Multiset();
        String st = "a, b, c, d, {a, b}";
        ms.formMultisetFromString(st);
        System.out.println(ms.toString());
    }


    public static void main(String[] args) {
        Multiset ms = new Multiset();
        String st = "a, b, {x,y}";
        ms.formMultisetFromString(st);
        System.out.println(ms.toString());
        System.out.println(ms.getElements().get(0));

    }
}
