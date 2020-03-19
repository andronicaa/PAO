package lab3;

public class Main {

    public static void main(String[] args) {
//        exercitiul 2
        System.out.println("Date exercitiu_2: Persoana");
        Person student = new Person("Andronic", "Alexandra", "20", 123255, "student");
        Person teacher = new Person("Popescu", "Pop", "65", 456321, "teacher");
        student.displayPerson();
        teacher.displayPerson();
//        exercitiul 3
        System.out.println("Date exercitiu_3: Camera");
        Room room1 = new Room("12A", "normal", 3);
        Room room2 = new Room("12B", "tech", 7);
        room1.displayRoom();
        room2.displayRoom();

//        exercitiul 4
        System.out.println("Date exercitiu 4: Subject");
        Subject subject1 = new Subject(room1, 23, teacher);
        subject1.displaySubject();


    }


}
