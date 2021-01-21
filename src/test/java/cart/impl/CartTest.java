package cart.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;
import receipt.IReceipt;
import receipt.impl.ReceiptProduct;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {

    private final static BigDecimal TWENTY = BigDecimal.valueOf(20L);
    private final static BigDecimal TWO = BigDecimal.valueOf(2L);
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testThatTheBillForAnEmptyCartIsCorrectlyInitialized() {
        IReceipt bill = cart.bill();

        assertThat(BigDecimal.ZERO, comparesEqualTo(bill.getTotal()));
        assertThat(BigDecimal.ZERO, comparesEqualTo(bill.getTaxes()));
        assertTrue(bill.billedItems().isEmpty());
    }

    @Test
    void testThatTheBillForOneItemIsCorrectlyEvaluated() {
        ITaxableProduct product = createTaxableProduct("product1");
        cart.add(product, 1);
        IReceipt bill = cart.bill();

        assertThat(BigDecimal.TEN, comparesEqualTo(bill.getTotal()));
        assertThat(BigDecimal.ONE, comparesEqualTo(bill.getTaxes()));
        assertEquals(1, bill.billedItems().size());
        assertThat(bill.billedItems(), containsInAnyOrder(
                new ReceiptProduct("product1", 1, BigDecimal.TEN)
        ));
    }

    @Test
    void testThatTheBillForTwoDifferentItemsIsCorrectlyEvaluated() {
        ITaxableProduct product1 = createTaxableProduct("product1");
        cart.add(product1, 1);
        ITaxableProduct product2 = createTaxableProduct("product2");
        cart.add(product2, 1);
        IReceipt bill = cart.bill();

        assertThat(TWENTY, comparesEqualTo(bill.getTotal()));
        assertThat(TWO, comparesEqualTo(bill.getTaxes()));
        assertEquals(2, bill.billedItems().size());
        assertThat(bill.billedItems(), containsInAnyOrder(
                new ReceiptProduct("product1", 1, BigDecimal.TEN),
                new ReceiptProduct("product2", 1, BigDecimal.TEN)
        ));
    }

    @Test
    void testThatTheBillForTwoItemsOfTheSameProductIsCorrectlyEvaluated() {
        ITaxableProduct product = createTaxableProduct("product1");
        cart.add(product, 2);
        IReceipt bill = cart.bill();

        assertThat(TWENTY, comparesEqualTo(bill.getTotal()));
        assertThat(TWO, comparesEqualTo(bill.getTaxes()));
        assertEquals(1, bill.billedItems().size());
        assertThat(bill.billedItems(), containsInAnyOrder(
                new ReceiptProduct("product1", 2, BigDecimal.valueOf(20L))
        ));
    }

    private ITaxableProduct createTaxableProduct(String name) {
        ITaxableProduct product = mock(ITaxableProduct.class);
        when(product.getProduct()).thenReturn(name);
        when(product.getTaxes()).thenReturn(BigDecimal.ONE);
        when(product.getTaxedPrice()).thenReturn(BigDecimal.TEN);
        return product;
    }
}