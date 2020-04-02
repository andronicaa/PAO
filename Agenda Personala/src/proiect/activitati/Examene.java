package proiect.activitati;

public class Examene extends Activitate {

    private String curs;

    public Examene(String numeActivitate, String data, String prioritate, String locatie, String curs) {
        super(numeActivitate, data, prioritate, locatie);
        this.curs = curs;
    }



    public String getCurs() {
        return curs;
    }



    public void setCurs(String curs) {
        this.curs = curs;
    }

    @Override
    public String toString() {
        return "Examene{" +
                "Data='" + super.getData() + '\'' +
                ", Curs='" + curs + '\'' +
                '}';
    }
}
