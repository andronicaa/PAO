package lab3;

public class Subject {
    private Room room;
    private int noOfStudents;
    private Person teacher;

    public Subject(Room room, int noOfStudents, Person teacher) {
        this.room = room;
        this.noOfStudents = noOfStudents;
        this.teacher = teacher;
    }
    public Subject(String room, String type_room, int floor, int noOfStudents, String name, String surname, String age, long id_number, String type) {
        this.room = new Room(room, type_room, floor);
        this.noOfStudents = noOfStudents;
        this.teacher = new Person(name, surname, age, id_number, type);
    }
    public Room getRoom() {
        return room;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }
    public void displaySubject() {
        room.displayRoom();
        System.out.println(noOfStudents);
        teacher.displayPerson();
    }
}
