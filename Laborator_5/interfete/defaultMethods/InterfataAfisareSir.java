package interfete.defaultMethods;

public interface InterfataAfisareSir {
    default void afiseazaSit(String str) {
        if(!esteSirVid(str))
            System.out.println("Ssitul: " + str);
        else
            System.out.println("Siturl este vid");
    }

    static boolean esteSirVid(String str) {
        System.out.println("Metoda esteSirVid din interfata");
        return str == null ? true: (str.equals("") ? true: false);

    }
}
