package telran.strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.SourceVersion;

public class Strings {
    static Pattern pattern;
    static {
        pattern = Pattern.compile(getArithmeticExpressionRegex());
    }

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
        return SourceVersion.isKeyword(name);
    }

    
    
    
    public static boolean isArithmeticExpression(String expr) {
        Matcher matcher = pattern.matcher(expr);
        boolean exprMatch = matcher.matches(); //expr.matches(getArithmeticExpressionRegex())
        boolean pairness = areBracketsBalanced(expr); //areBracketsBalanced(expr)
        boolean javaNames = !containsKeywords(expr); //!containsKeywords(expr)
        return  exprMatch && pairness && javaNames; 
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

    private static String getOperator() {
        return "[*/+-]";
    }

    private static String getOperand() {
        String javaVar = javaVariable();
        String number = "(\\d+(\\.\\d+)?)";
        String spacesBrackets = "([\\s(]*|[\\s)]*)";
        return String.format("%s(%s|%s)%s", spacesBrackets,javaVar,number,spacesBrackets);
    }
    
    private static String getArithmeticExpressionRegex() {
        String operand = getOperand();
        String operator = getOperator();
        return String.format("%s(%s%s)*",operand, operator, operand);
    }

    private static boolean containsKeywords(String expr) {
        String[] tokens = expr.split(operatorSpacesBrackets());
        return Arrays.stream(tokens).anyMatch(Strings::isJavaKeyword);
    }

    private static String operatorSpacesBrackets(){
        return String.format("%s|[\\s()]+", getOperator());
    }




    

}
