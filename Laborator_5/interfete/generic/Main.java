package interfete.generic;

public class Main {
    public static void main(String[] args) {
        Producer<Car> carProducer = new CarProducer<Car>();
        Car car = carProducer.produce();
    }
}
