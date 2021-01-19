package cart.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;
import receipt.IReceipt;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {

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
        assertTrue(bill.getCartContent().isEmpty());
    }


    @Test
    void testThatTheBillForOneItemIsCorrectlyEvaluated() {
        ITaxableProduct product = createTaxableProduct();
        cart.add(product, 1);
        IReceipt bill = cart.bill();

        assertThat(BigDecimal.TEN, comparesEqualTo(bill.getTotal()));
        assertThat(BigDecimal.ONE, comparesEqualTo(bill.getTaxes()));
        assertFalse(bill.getCartContent().isEmpty());
    }

    private ITaxableProduct createTaxableProduct() {
        ITaxableProduct product = mock(ITaxableProduct.class);
        when(product.getProduct()).thenReturn("product");
        when(product.getTaxes()).thenReturn(BigDecimal.ONE);
        when(product.getTaxedPrice()).thenReturn(BigDecimal.TEN);
        return product;
    }


    @Test
    void testThatTheBillForTwoDifferentItemsIsCorrectlyEvaluated() {
        ITaxableProduct product1 = createTaxableProduct();
        cart.add(product1, 1);
        ITaxableProduct product2 = createTaxableProduct();
        cart.add(product2, 1);
        IReceipt bill = cart.bill();

        assertThat(BigDecimal.valueOf(20L), comparesEqualTo(bill.getTotal()));
        assertThat(BigDecimal.valueOf(2L), comparesEqualTo(bill.getTaxes()));
        assertFalse(bill.getCartContent().isEmpty());
    }

    @Test
    void testThatTheBillForTwoItemsOfTheSameProductIsCorrectlyEvaluated() {
        ITaxableProduct product = createTaxableProduct();
        cart.add(product, 2);
        IReceipt bill = cart.bill();

        assertThat(BigDecimal.valueOf(20L), comparesEqualTo(bill.getTotal()));
        assertThat(BigDecimal.valueOf(2L), comparesEqualTo(bill.getTaxes()));
        assertFalse(bill.getCartContent().isEmpty());
    }
}