package telran.strings.test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import telran.strings.Strings;

public class RegularExpressionsTest {

    @Test
    public void testValidJavaVariableNames() {
        String isJavaVar = Strings.javaVariable();
        List<String> validVar = Arrays.asList("varName", "var_1", "_varName", "varName123", "VarName", "___", "$f", "$$$$");
        boolean allMatch = validVar.stream().allMatch(name -> name.matches(isJavaVar));
        assertTrue(allMatch);

        List<String> invalidVar = Arrays.asList("1varName", "var-Name", "var Name", "@varName", "", "_");
        boolean noneMatch = invalidVar.stream().noneMatch(name -> name.matches(isJavaVar));
        assertTrue(noneMatch);
    }

    @Test
    void number0_300Test(){
        String regex = Strings.number0_300();
        assertTrue("0".matches(regex));
        assertTrue("8".matches(regex));
        assertTrue("12".matches(regex));
        assertTrue("150".matches(regex));
        assertTrue("300".matches(regex));
        assertFalse("01".matches(regex));
        assertFalse("301".matches(regex));
        assertFalse("00".matches(regex));
        assertFalse("".matches(regex));
        assertFalse(" 1".matches(regex));
    }

    @Test
    void ipV4octetTest(){
        String regex = Strings.ipV4octet();
        assertTrue("0".matches(regex));
        assertTrue("00".matches(regex));
        assertTrue("000".matches(regex));
        assertTrue("10".matches(regex));
        assertTrue("100".matches(regex));
        assertTrue("255".matches(regex));
        assertTrue("199".matches(regex));
        assertTrue("249".matches(regex));


        assertFalse(" 01".matches(regex));
        assertFalse("0t1".matches(regex));
        assertFalse("256".matches(regex));
        assertFalse("-1".matches(regex));
        assertFalse("".matches(regex));
        assertFalse("0000".matches(regex));
        assertFalse("0000".matches(regex));
        assertFalse("t".matches(regex));
        assertFalse("-1".matches(regex));
        assertFalse("1111".matches(regex));
        assertFalse("0001".matches(regex));
        assertFalse("256".matches(regex));
        assertFalse("300".matches(regex));
        assertFalse("*".matches(regex));
        assertFalse("1 ".matches(regex));
    }

    @Test
    void ipV4adressTest(){
        String regex = Strings.ipV4adress();
        assertTrue("0.0.0.0".matches(regex));
        assertTrue("255.255.255.255".matches(regex));

        assertFalse("0.0.0".matches(regex));
        assertFalse("0.0.0.256".matches(regex));
        assertFalse("0.0.0+255".matches(regex));

    }

    @Test
    void stringWithJavaNamesTest(){
        String names = "123 1a _ abs int enum null lmn";
        String regex = Strings.stringWithJavaNames(names);
        assertEquals("abs lmn", regex);
        assertNotEquals("123 1a _ abs int enum null lmn", regex);

    }

    @Test
    public void getArithmeticExpressionTest() {
        assertTrue(Strings.isArithmeticExpression("a + b"));
        assertTrue(Strings.isArithmeticExpression("3.5 * (x + y)"));
        assertTrue(Strings.isArithmeticExpression("( (  a   ) ) - 7 / (b + 1)"));
        assertTrue(Strings.isArithmeticExpression("x"));
        assertTrue(Strings.isArithmeticExpression("(a + b) * c"));
        assertTrue(Strings.isArithmeticExpression("(  a + (b * c )  ) - ( ( (   d) / e ) )"));
        assertTrue(Strings.isArithmeticExpression("42 + a"));
        assertTrue(Strings.isArithmeticExpression("123.6"));
        assertTrue(Strings.isArithmeticExpression("aA1 + b__f - c * d / e"));
        assertTrue(Strings.isArithmeticExpression("for1 + 3"));
        assertTrue(Strings.isArithmeticExpression("  (    (   (   ( ( a )  +  b )  /  2  ) + (10)   )    )  "));
        assertTrue(Strings.isArithmeticExpression("   (    fo  +  r  )   "));
        assertTrue(Strings.isArithmeticExpression("((  ((  a))  ) )"));


        assertFalse(Strings.isArithmeticExpression("a +"));
        assertFalse(Strings.isArithmeticExpression("* b"));
        assertFalse(Strings.isArithmeticExpression("(a + b"));
        assertFalse(Strings.isArithmeticExpression("a + b)"));
        assertFalse(Strings.isArithmeticExpression("(a + b))"));
        assertFalse(Strings.isArithmeticExpression("((a + b)"));
        assertFalse(Strings.isArithmeticExpression("a + (b * )"));
        assertFalse(Strings.isArithmeticExpression("a + (b * c - d"));
        assertFalse(Strings.isArithmeticExpression("a + / b"));
        assertFalse(Strings.isArithmeticExpression("42.6 ++ a"));
        assertFalse(Strings.isArithmeticExpression(""));
        assertFalse(Strings.isArithmeticExpression("   "));
        assertFalse(Strings.isArithmeticExpression("a + b - * c"));
        assertFalse(Strings.isArithmeticExpression("a + (  b - c * d"));
        assertFalse(Strings.isArithmeticExpression("a + b - (c * d) /"));
        assertFalse(Strings.isArithmeticExpression("for + 3"));
        assertFalse(Strings.isArithmeticExpression("1for + 3"));
        assertFalse(Strings.isArithmeticExpression("a b + 3"));
        assertFalse(Strings.isArithmeticExpression("a (b)"));
        assertFalse(Strings.isArithmeticExpression("a -(b)4"));
        assertFalse(Strings.isArithmeticExpression("abstract + 3"));
        assertFalse(Strings.isArithmeticExpression("()a + 3"));


    }

}