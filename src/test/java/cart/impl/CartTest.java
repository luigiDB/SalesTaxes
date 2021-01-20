package cart.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;
import receipt.IReceipt;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {

    private final BigDecimal TWENTY = BigDecimal.valueOf(20L);
    private final BigDecimal TWO = BigDecimal.valueOf(2L);
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
        assertFalse(bill.billedItems().isEmpty());
    }

    private ITaxableProduct createTaxableProduct(String name) {
        ITaxableProduct product = mock(ITaxableProduct.class);
        when(product.getProduct()).thenReturn(name);
        when(product.getTaxes()).thenReturn(BigDecimal.ONE);
        when(product.getTaxedPrice()).thenReturn(BigDecimal.TEN);
        return product;
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
        assertFalse(bill.billedItems().isEmpty());
    }

    @Test
    void testThatTheBillForTwoItemsOfTheSameProductIsCorrectlyEvaluated() {
        ITaxableProduct product = createTaxableProduct("product1");
        cart.add(product, 2);
        IReceipt bill = cart.bill();

        assertThat(TWENTY, comparesEqualTo(bill.getTotal()));
        assertThat(TWO, comparesEqualTo(bill.getTaxes()));
        assertFalse(bill.billedItems().isEmpty());
    }
}