package interfete.generic;

public class CarProducer<T> implements Producer<T> {
    @Override
    public T produce() {
        return (T) new Car();
    }
}
