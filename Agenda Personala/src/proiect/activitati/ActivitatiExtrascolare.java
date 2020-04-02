package proiect.activitati;

public class ActivitatiExtrascolare extends Activitate{
    private String tipActivitate;

    public ActivitatiExtrascolare(String numeActivitate, String data, String prioritate, String locatie, String tipActivitate) {
        super(numeActivitate, data, prioritate, locatie);
        this.tipActivitate = tipActivitate;
    }

    public String getTipActivitate() {
        return tipActivitate;
    }

    public void setTipActivitate(String tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    @Override
    public String toString() {
        return "ActivitatiExtrascolare{" +
                " Data= " + super.getData() +
                " Locatie= " + super.getLocatie() +
                " tipActivitate='" + tipActivitate + '\'' +
                '}';
    }
}
