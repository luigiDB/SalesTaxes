package receipt.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.impl.TaxableProduct;
import receipt.IReceipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StdOutReceiptPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    String newLine = System.getProperty("line.separator");
    private StdOutReceiptPrinter stdOutReceiptPrinter;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        stdOutReceiptPrinter = new StdOutReceiptPrinter();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testThatThePrintedRecipeRespectTheFormat() {
        String expectedOutput = String.join(newLine,
                "1 \tbook: \t12.49",
                "2 \timported chocolate: \t1.70",
                "Sales Taxes: 1.55",
                "Total: 14.19",
                ""
        );

        IReceipt receipt = mock(IReceipt.class);
        when(receipt.billedItems()).thenReturn(List.of(
                new ReceiptProduct("book", 1, BigDecimal.valueOf(12.49)),
                new ReceiptProduct("imported chocolate", 2, BigDecimal.valueOf(1.70))
        ));
        when(receipt.getTaxes()).thenReturn(BigDecimal.valueOf(1.55));
        when(receipt.getTotal()).thenReturn(BigDecimal.valueOf(14.19));

        stdOutReceiptPrinter.print(receipt);

        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    void testThatNullInputsAreRejected() {
        assertThrows(NullPointerException.class, () -> {
            stdOutReceiptPrinter.print(null);
        });
    }
}