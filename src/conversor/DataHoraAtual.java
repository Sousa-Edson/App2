package conversor;

import java.util.Calendar;
import java.util.Date;

public class DataHoraAtual {

    public Date DataAtual() {
        Date dataSistema = new Date();
        return dataSistema;
    }

    public String HoraAtual() {
        Calendar now = Calendar.getInstance();
        return String.format("%1$tH:%1$tM", now);

    }
}
