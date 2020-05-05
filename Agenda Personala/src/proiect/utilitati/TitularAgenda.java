package proiect.utilitati;


public class TitularAgenda{
//    fac aceasta clasa de tip singleton pentru ca doar un singur "titular" poate avea o agenda
    private static TitularAgenda single_instance = null;
    public String nume;
    public String prenume;
    public String nrTelefon;
    public int salariu;
    public String email;

   private TitularAgenda() {
    }

    public static TitularAgenda TitularAgenda() {
       if (single_instance == null) {
           single_instance = new TitularAgenda();
       }
       return single_instance;
    }

    public int salariuDupaPlati(int factura) {

        if (salariu > factura)
            salariu -= factura;
        else
            return -1; //inseamna ca factura nu se poate plati
        return salariu;
    }

    @Override
    public String toString() {
        return "TitularAgenda{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", salariu=" + salariu +
                ", email='" + email + '\'' +
                '}';
    }
}
