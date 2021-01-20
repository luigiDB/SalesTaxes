package receipt.impl;

import org.apache.commons.lang3.tuple.Triple;
import receipt.IReceipt;
import receipt.IReceiptPrinter;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class StdOutReceiptPrinter implements IReceiptPrinter {

    private final String newLine = System.getProperty("line.separator");

    @Override
    public void print(IReceipt receipt) {
        for (IReceiptProduct item: receipt.billedItems()) {
            System.out.printf("%d \t%s: \t%s%s",
                    item.getQuantity(),
                    item.getProduct(),
                    formatBigDecimal(item.getPrice()),
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
