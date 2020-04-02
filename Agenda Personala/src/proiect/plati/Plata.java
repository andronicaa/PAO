package proiect.plati;

import java.util.ArrayList;

public class Plata {
    private String factura;
    private String dataFacturare;
    private String dataScadenta;
    private int pret;
    public Plata(String factura, String dataFacturare, String dataScadenta, int pret) {
        this.factura = factura;
        this.dataFacturare = dataFacturare;
        this.dataScadenta = dataScadenta;
        this.pret = pret;
    }


    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getDataFacturare() {
        return dataFacturare;
    }

    public void setDataFacturare(String dataFacturare) {
        this.dataFacturare = dataFacturare;
    }

    public void setDataScadenta(String dataScadenta) {
        this.dataScadenta = dataScadenta;
    }
    public void setPret(int pret) {this.pret = pret;}

    public String getFactura() {
        return factura;
    }

    public String getDataScadenta() {
        return dataScadenta;
    }
    public int getPret() {return pret;}

}
