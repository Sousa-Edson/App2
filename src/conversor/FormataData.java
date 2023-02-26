package conversor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

    DateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");

    public Date dataFormato(String dt) throws ParseException {
        return df.parse(dt);

    }
}
