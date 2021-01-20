package receipt.impl;

import org.apache.commons.lang3.tuple.Triple;
import receipt.IReceipt;
import receipt.IReceiptPrinter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StdOutReceiptPrinter implements IReceiptPrinter {

    private final String newLine = System.getProperty("line.separator");

    @Override
    public void print(IReceipt receipt) {
        for (Triple<String, Integer, BigDecimal> item: receipt.billedItems()) {
            System.out.printf("%d \t%s: \t%s%s",
                    item.getMiddle(),
                    item.getLeft(),
                    formatBigDecimal(item.getRight()),
                    newLine);
        }
        System.out.printf("Sales Taxes: %s%s",
                formatBigDecimal(receipt.getTaxes()),
                newLine);
        System.out.printf("Total: %s%s",
                formatBigDecimal(receipt.getTotal()),
                newLine);
    }

    private static String formatBigDecimal(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("#,####.00");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(number);
    }
}
