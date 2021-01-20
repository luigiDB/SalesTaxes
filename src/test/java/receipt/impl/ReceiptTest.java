package receipt.impl;


import cart.IBilledProduct;
import cart.impl.BilledProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceiptTest {

    private final static BigDecimal TOTAL = BigDecimal.valueOf(11L);
    private final static BigDecimal TAXES = BigDecimal.ONE;
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        List<IBilledProduct> cartItems = List.of(
                new BilledProduct(initProduct("product1"), 1),
                new BilledProduct(initProduct("product2"), 2)
        );
        receipt = new Receipt(cartItems, TOTAL, TAXES);
    }

    private ITaxableProduct initProduct(String name) {
        ITaxableProduct mock = mock(ITaxableProduct.class);
        when(mock.getProduct()).thenReturn(name);
        when(mock.getTaxedPrice()).thenReturn(BigDecimal.ONE);
        return mock;
    }

    @Test
    void testThatAllProductsAndQuantitiesArePresent() {
        assertTrue(receipt.billedItems().contains(new ReceiptProduct("product1", 1, BigDecimal.ONE)));
        assertTrue(receipt.billedItems().contains(new ReceiptProduct("product2", 2, BigDecimal.valueOf(2L))));
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