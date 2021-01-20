package integration;

import cart.impl.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.impl.TaxableProduct;
import product.strategy.impl.NoTaxStrategy;
import product.strategy.impl.RoundedPercentageStrategy;
import receipt.IReceipt;
import receipt.IReceiptPrinter;
import receipt.impl.StdOutReceiptPrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E2eTest {

    private final BigDecimal FIVE = BigDecimal.valueOf(5L);
    private final BigDecimal TEN = BigDecimal.TEN;
    private Cart cart;
    private IReceiptPrinter printer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String newLine = System.getProperty("line.separator");

    @BeforeEach
    void setUp() {
        cart = new Cart();
        printer = new StdOutReceiptPrinter();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testCartWithoutImportedItems() {
        cart.add(new TaxableProduct("book", BigDecimal.valueOf(12.49), new NoTaxStrategy(), new NoTaxStrategy()), 1);
        cart.add(new TaxableProduct("music CD", BigDecimal.valueOf(14.99), new RoundedPercentageStrategy(BigDecimal.TEN), new NoTaxStrategy()), 1);
        cart.add(new TaxableProduct("chocolate bar", BigDecimal.valueOf(0.85), new NoTaxStrategy(), new NoTaxStrategy()), 1);

        IReceipt receipt = cart.bill();
        printer.print(receipt);

        String expectedOutput = String.join(newLine,
                "1 \tbook: \t12.49",
                "1 \tmusic CD: \t16.49",
                "1 \tchocolate bar: \t0.85",
                "Sales Taxes: 1.50",
                "Total: 29.83",
                ""
        );

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testCartWithOnlyImportedItems() {
        cart.add(new TaxableProduct("imported box of chocolates", BigDecimal.valueOf(10.00), new NoTaxStrategy(), new RoundedPercentageStrategy(FIVE)), 1);
        cart.add(new TaxableProduct("imported bottle of perfume", BigDecimal.valueOf(47.50), new RoundedPercentageStrategy(TEN), new RoundedPercentageStrategy(FIVE)), 1);

        IReceipt receipt = cart.bill();
        printer.print(receipt);

        String expectedOutput = String.join(newLine,
                "1 \timported box of chocolates: \t10.50",
                "1 \timported bottle of perfume: \t54.65",
                "Sales Taxes: 7.65",
                "Total: 65.15",
                ""
        );

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testMixedCart() {
        cart.add(new TaxableProduct("imported bottle of perfume", BigDecimal.valueOf(27.99), new RoundedPercentageStrategy(TEN), new RoundedPercentageStrategy(FIVE)), 1);
        cart.add(new TaxableProduct("bottle of perfume", BigDecimal.valueOf(18.99), new RoundedPercentageStrategy(TEN), new NoTaxStrategy()), 1);
        cart.add(new TaxableProduct("pack of headache pills", BigDecimal.valueOf(9.75), new NoTaxStrategy(), new NoTaxStrategy()), 1);
        cart.add(new TaxableProduct("box of imported chocolate", BigDecimal.valueOf(11.25), new NoTaxStrategy(), new RoundedPercentageStrategy(FIVE)), 1);

        IReceipt receipt = cart.bill();
        printer.print(receipt);

        String expectedOutput = String.join(newLine,
                "1 \timported bottle of perfume: \t32.19",
                "1 \tbottle of perfume: \t20.89",
                "1 \tpack of headache pills: \t9.75",
                "1 \tbox of imported chocolate: \t11.85",
                "Sales Taxes: 6.70",
                "Total: 74.68",
                ""
        );

        assertEquals(expectedOutput, outContent.toString());
    }
}
