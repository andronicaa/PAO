package immutable_class;

public final class Immutable_Student {
//    make your class final, so that no other classes can extend it
//    data memberss in the class must be declared as final => so that they are initialized only once inside the constructor and never modified afterward
//    don't expose setter
//    then exposing methods which modify the state of the class, you must always return a new instance of that class
    private final int id;
    private final String name;
    private final Age age;

    public Immutable_Student(int id, String name, Age age) {
        this.id = id;
        this.name = name;
//        trebuei sa dam o copie si niciodata obiectul insasi
        this.age = new Age(age.getDay(), age.getMonth(), age.getYear());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Age getAge() {
        return age;
    }
}
