package interfete.defaultMethods;

public class AfisareSir implements InterfataAfisareSir {
    public static boolean esteSirVid(String str) {
        System.out.println("Metoda esteSirVid din clasa");
        return str.length() == 0;
    }
}
