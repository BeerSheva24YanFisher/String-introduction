package telran.strings;

import java.util.Arrays;

public class Strings {
    public static String[] javaKeywords = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
        "class", "const", "continue", "default", "do", "double", "else", "enum",
        "extends", "final", "finally", "float", "for", "goto", "if", "implements",
        "import", "instanceof", "int", "interface", "long", "native", "new", "null",
        "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try",
        "void", "volatile", "while"
    };

    public static String firstName() {
        return "[A-Z][a-z]{4,}";
    }

    public static String javaVariable(){
        return "((?!_$)[a-zA-Z_$][\\w$]*)";
    }

    public static String number0_300() {
        return "0|[1-9]\\d?|[1-2]\\d\\d|300";        
    }

    public static String ipV4octet(){
        
        return "(2(5[0-5]|[0-4]\\d)|[01]?\\d\\d?)";
    }

    public static String ipV4adress(){
        String octet = ipV4octet();
        return String.format("%s(\\.%s){3}", octet, octet);
    }

    public static String stringWithJavaNames(String names){
        String[] namesArray = names.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String name : namesArray) {
            if (name.matches(javaVariable())&&!isJavaKeyword(name)) {
                result.append(name).append(" ");
            }                        
        }

        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }

    private static boolean isJavaKeyword(String name) {
        return Arrays.binarySearch(javaKeywords, name)>0;
    }

    
    
    
    public static boolean isArithmeticExpression(String expr) {
        return areBracketsBalanced(expr) && !containsKeywords(expr) && expr.matches(getArithmeticExpressionRegex());    
    }

    private static boolean areBracketsBalanced(String expr) {
        int counter = 0;
        int index = 0;
        while (index < expr.length() && counter >= 0) {
            char c = expr.charAt(index);
            switch (c) {
                case '(' -> counter++;
                case ')' -> counter--;
            }
            index++;
        }
        return counter == 0;
    }

    private static String getNumberRegex() {
        return "(\\d+(\\.\\d+)?)";
    }

    private static String getArithmeticExpressionRegex() {
        String javaVar = javaVariable();
        String number = getNumberRegex();
        String spaceAndBracketBegin = "(\\s*\\(*)*";
        String spaceAndBracketEnd = "(\\s*\\)*)*";
        String operator = "[*/+-]";
        return String.format(
            "%s(%s|%s)%s(%s%s(%s|%s)%s)*",
            spaceAndBracketBegin, javaVar, number,spaceAndBracketEnd,operator,spaceAndBracketBegin, javaVar, number, spaceAndBracketEnd);
    }

    private static boolean containsKeywords(String expr) {
        String[] tokens = expr.split("[+\\-*/()\\s]");
        return Arrays.stream(tokens).anyMatch(Strings::isJavaKeyword);
    }



    

}
