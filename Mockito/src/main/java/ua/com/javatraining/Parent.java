package ua.com.javatraining;

public class Parent {

    private Child child;

    public Parent() {
        System.out.println("parent constructor");
    }

    public Parent(Child child) {
        this.child = child;
        System.out.println("parent constructor with params");
    }

    public Child getChild() {
        return child;
    }

    public String printName() {
        System.out.println("parent");
        child.printName();
        return "return parent printName";
    }

}
