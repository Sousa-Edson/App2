package conversor;

import java.text.NumberFormat;
import java.util.Locale;

public class FormataMilhar {

    public double toStringForDouble(String txt) {
        try {
            return Double.parseDouble(txt.replace(".", "").replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String toDoubleForString(Double d) {
        try {
            Locale ptBr = new Locale("pt", "BR");
            return NumberFormat.getInstance(ptBr).format(d);
        } catch (Exception e) {
            return "";
        }
    }
}
