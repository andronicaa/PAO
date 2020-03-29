package interfete;

public class Main {
    public static void main(String[] args) {
        Exemplul1 obj = new Exemplul1();
        obj.sayHello();
//        you cannot create instances of a Java interface by itself
//        you must always create and instance of some class that implements the interface and
//        reference that instance as an instance of the interface
        Interfaceexemplul1 obj_2 = new Exemplul1();
        obj_2.sayHello();

//        static methods don't depend on the nedd to create obj of a class
//        you can refer them by the class name itslef
        InterfaceExemplul3.print("Sal cf");
    }
}
