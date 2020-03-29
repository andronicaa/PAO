package stringuri;

public class Exemplu1 {
    public static void main(String[] args) {
        String s1 = "masina"; //string literal

        String s2 = new String("alta masina"); //folosind cuvantul new

        s1.toUpperCase();
        System.out.println(s1); //se va afisa tot masina
        String substring = s1.substring(1,2);
        System.out.println(substring);
    }
}
