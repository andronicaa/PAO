package asociere.agregare;

public class TestDepartment {
    public static void main(String[] args) {
        Student s1 = new Student("Andronic", "Alexandra", 4);
        Student s2 = new Student("Popescu", "Ion", 45);

        Student[] students = {s1, s2};
        Department department = new Department("IT", students);

        System.out.println(department.getName());
        System.out.println(department.getStudents().length);
    }
}
