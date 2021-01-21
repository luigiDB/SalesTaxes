package receipt.impl;

import receipt.IReceipt;
import receipt.IReceiptPrinter;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class StdOutReceiptPrinter implements IReceiptPrinter {

    private static final String NEW_LINE = System.getProperty("line.separator");

    @Override
    public void print(IReceipt receipt) {
        Objects.requireNonNull(receipt);
        for (IReceiptProduct item : receipt.billedItems()) {
            System.out.printf("%d \t%s: \t%s%s",
                    item.getQuantity(),
                    item.getProduct(),
                    formatBigDecimal(item.getPrice()),
                    NEW_LINE);
        }
        System.out.printf("Sales Taxes: %s%s",
                formatBigDecimal(receipt.getTaxes()),
                NEW_LINE);
        System.out.printf("Total: %s%s",
                formatBigDecimal(receipt.getTotal()),
                NEW_LINE);
    }

    private static String formatBigDecimal(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("#,###0.00");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(number);
    }
}
