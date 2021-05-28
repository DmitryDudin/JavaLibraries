package ua.com.javatraining;

@AnnotationForParsing
public class TestClassForParsing extends ParentTestClass implements TestClassInterface {

    private String privateField;
    String packagePrivateField;
    protected String protectedField;
    public String publicField;

    private static String publicStaticField;
    public static final String publicStaticFinalField = "init";


    {
        privateField = "privF";
    }

    static {
        publicStaticField = "pubSF";
    }

    public TestClassForParsing() {
    }

    public TestClassForParsing(String privateField, String packagePrivateField, String protectedField, String publicField) {
        this.privateField = privateField;
        this.packagePrivateField = packagePrivateField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    public void publicServiceMethod(String param) {
        System.out.println("testServiceMethod -> " + param);
        System.out.println(privateServiceMethod(11));
    }

    private String privateServiceMethod(Integer value) {
        System.out.println("privateServiceMethod -> " + value);
        return String.valueOf(value);
    }

    public static String publicStaticMEethod(Double d) {
        System.out.println("publicStaticMEethod -> " + d);
        return String.valueOf(d);
    }


}
