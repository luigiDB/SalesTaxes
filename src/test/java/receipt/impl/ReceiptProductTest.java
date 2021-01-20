package receipt.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.impl.TaxableProduct;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}