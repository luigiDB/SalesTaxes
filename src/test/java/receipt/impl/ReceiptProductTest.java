package receipt.impl;

import cart.impl.BilledProduct;
import com.google.common.testing.EqualsTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.impl.TaxableProduct;
import receipt.IReceiptProduct;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptProductTest {

    private ReceiptProduct receiptProduct;

    @BeforeEach
    void setUp() {
        receiptProduct = new ReceiptProduct("product", 42, BigDecimal.valueOf(420L));
    }

    @Test
    void testThatConstructorsAreGeneratingTheSameObject() {
        IReceiptProduct productFromStaticConstructor = ReceiptProduct.from(
                new BilledProduct(
                        new TaxableProduct("product", BigDecimal.TEN, (price) -> BigDecimal.ZERO, (price) -> BigDecimal.ZERO),
                        42
                ));
        // The following method offered from the Guava library is testing that the two constructors are generating
        // the same ReceiptProduct using the equals and hashCode methods ensuring that the results are consistent.
        new EqualsTester()
                .addEqualityGroup(receiptProduct, productFromStaticConstructor)
                .testEquals();
    }

    @Test
    void getProduct() {
        assertEquals("product", receiptProduct.getProduct());
    }

    @Test
    void getQuantity() {
        assertEquals(42, receiptProduct.getQuantity());
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.valueOf(420L), receiptProduct.getPrice());
    }

    @Test
    void testEquals() {
        assertNotEquals(new ReceiptProduct("product2", 42, BigDecimal.valueOf(420L)), receiptProduct);
        assertNotEquals(new ReceiptProduct("product", 4242, BigDecimal.valueOf(420L)), receiptProduct);
        assertNotEquals(new ReceiptProduct("product", 42, BigDecimal.valueOf(42L)), receiptProduct);
    }
}