package receipt.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

class ReceiptTest {

    private final IReceiptProduct PRODUCT_1 = new ReceiptProduct("product1", 1, BigDecimal.ONE);
    private final IReceiptProduct PRODUCT_2 = new ReceiptProduct("product2", 2, BigDecimal.TEN);
    private final BigDecimal TOTAL = BigDecimal.valueOf(11L);
    private final BigDecimal TAXES = BigDecimal.ONE;
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        List<IReceiptProduct> cartItems = List.of(
                PRODUCT_1,
                PRODUCT_2
        );
        receipt = new Receipt(cartItems, TOTAL, TAXES);
    }

    @Test
    void testThatAllProductsAndQuantitiesArePresent() {
        assertThat("cart equality without order",
                receipt.billedItems(), containsInAnyOrder(PRODUCT_1, PRODUCT_2));
    }

    @Test
    void testThatTotalIsReturned() {
        assertThat(receipt.getTotal(), comparesEqualTo(TOTAL));
    }

    @Test
    void testThatTaxesAreReturned() {
        assertThat(receipt.getTaxes(), comparesEqualTo(TAXES));
    }
}