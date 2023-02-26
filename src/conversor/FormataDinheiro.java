package conversor;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class FormataDinheiro {

    public String toStringForMoney(String txt) {
        try {
            BigDecimal df = new BigDecimal(Double.parseDouble(txt.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".").trim()));
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            nf.setMinimumFractionDigits(3);
            return nf.format(df);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String toDoubleForStringMoney(Double dValor) {
        try {
            BigDecimal df = new BigDecimal(dValor);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            nf.setMinimumFractionDigits(3);
            return nf.format(df);
        } catch (Exception e) {
            return null;
        }

    }
}
