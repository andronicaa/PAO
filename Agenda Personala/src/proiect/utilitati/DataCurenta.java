package proiect.utilitati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//clasa ce determina data curenta in diferite formate(data in totalitate sau doar luna ce imi va folosi la anumite metode)
public class DataCurenta {
//    extrag doar luna din data curenta
    public String lunaCurenta()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDate localDate = LocalDate.now();
        String data = dtf.format(localDate);
        return data;
    }
//    iau toata data de forma dd/MM/yyyy
    public String dataCurenta()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String data = dtf.format(localDate);
        return data;
    }

}
