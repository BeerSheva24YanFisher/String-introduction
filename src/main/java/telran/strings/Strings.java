package telran.strings;

public class Strings {
    public static String firstName() {
        return "[A-Z][a-z]{4,}";
    }

    public static String javaVariable(){
        return "(?!_$)[a-zA-Z_$][a-zA-Z0-9_$]*";
    }

}
