package lab3;

public class Person {
    private String name, surname, age, type;
    private long id_number;

    public Person() {
    }

    public Person(String name, String surname, String age, long id_number, String type) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id_number = id_number;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public long getId_number() {
        return id_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId_number(long id_number) {
        this.id_number = id_number;
    }
    public void displayPerson() {
        System.out.println(name + " " + surname + " " + type + " " + id_number + " " + age);
    }
}
