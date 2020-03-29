package interfete;

public class ImplementMultiple implements Interfaceexemplul1, InterfaceExemplul2 {
//    a Java class CAN implement MULTIPLE Java interfaces
//    in this case the class must implements all the methods declared in all the interfaces implmented
//    ALL VARIABLES IN AN INTERFACE ARE PUBLIC
    @Override
    public void sayHello() {
        System.out.println("Hello");
    }

    @Override
    public void sayBye() {
        System.out.println("Bye");

    }
}
