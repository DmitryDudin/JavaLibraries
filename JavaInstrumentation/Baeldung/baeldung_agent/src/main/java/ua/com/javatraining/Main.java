package ua.com.javatraining;

public class Main {

    public static void main(String[] args) {
        System.out.println(Main.class.getModule());
        System.out.println(Main.class.getModule().getName());
        System.out.println(Main.class.getModule().getDescriptor());
    }

}
