package receipt.impl;

import com.google.common.testing.EqualsTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.impl.TaxableProduct;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReceiptProductTest {

    private ReceiptProduct receiptProduct;

    @BeforeEach
    void setUp() {
        receiptProduct = new ReceiptProduct("product", 42, BigDecimal.valueOf(420L));
    }

    @Test
    void testFromConstructor() {
        assertEquals(receiptProduct, ReceiptProduct.from(new TaxableProduct("product", BigDecimal.TEN, (price) -> BigDecimal.ZERO, (price) -> BigDecimal.ZERO), 42));
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
        new EqualsTester()
                .addEqualityGroup(receiptProduct)
                .testEquals();
        assertFalse(receiptProduct.equals(new ReceiptProduct("product2", 42, BigDecimal.valueOf(420L))));
        assertFalse(receiptProduct.equals(new ReceiptProduct("product", 4242, BigDecimal.valueOf(420L))));
        assertFalse(receiptProduct.equals(new ReceiptProduct("product", 42, BigDecimal.valueOf(42L))));
    }
}