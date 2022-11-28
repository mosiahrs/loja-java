package utilidades;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Mosiah Silva <mosiahrabello at gmail.com>
 */
public class Utilidades {

    static NumberFormat converterEmMoeda
            = new DecimalFormat("R$, #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String doubleParaString(Double valor) {
        return converterEmMoeda.format(valor);
    }

}
