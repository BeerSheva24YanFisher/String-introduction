package telran.strings.test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import telran.strings.Strings;

public class RegularExpressionsTest {

    @Test
    public void testValidJavaVariableNames() {
        String isJavaVar = Strings.javaVariable();
        List<String> validVar = Arrays.asList("varName", "var_1", "_varName", "varName123", "VarName");
        boolean allMatch = validVar.stream().allMatch(name -> name.matches(isJavaVar));
        assertTrue(allMatch);

        List<String> invalidVar = Arrays.asList("1varName", "var-Name", "var Name", "@varName");
        boolean noneMatch = invalidVar.stream().noneMatch(name -> name.matches(isJavaVar));
        assertTrue(noneMatch);
    }

}