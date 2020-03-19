package lab3;
import java.util.Scanner;

public class Exercitiu1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] students = new int[20];
        int sum = 0, i;
        for (i = 0; i < students.length; i++){
            int value = scanner.nextInt();
            if (value == -1)
                break;
            else
            {
                students[i] = value;
                sum += value;
            }
        }
        System.out.println((double)sum / (double)i);
    }

}
