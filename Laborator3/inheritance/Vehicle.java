package inheritance;

public class Vehicle {
    public static void main(String[] args)
    {   System.out.println("Obiect de tip car: ");
        Car c1 = new Car();
        System.out.println(c1.seats);
        System.out.println(c1.getDoors());

        System.out.println("Obiect de tip autobuz: ");
        Bus b1 = new Bus();
        System.out.println(b1.seats);
        System.out.println(b1.getDoors());


        System.out.println("Upcasting: ");
        Car c2 =  new Bus();
        System.out.println(c2.seats);
        System.out.println(c2.getDoors());

    }


}
