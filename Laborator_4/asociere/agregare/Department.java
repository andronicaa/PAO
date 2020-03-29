package asociere.agregare;

public class Department {
    private String name;
    private Student[] students; //agregare, acest obiect de tipul student poate sa existe si fara obiectul de tipul department

    public Department(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
