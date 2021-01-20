package cart.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BilledProductTest {

    private BilledProduct billedProduct;
    private ITaxableProduct mockProduct;

    @BeforeEach
    void setUp() {
        mockProduct = mock(ITaxableProduct.class);
        billedProduct = new BilledProduct(mockProduct, 42);
    }

    @Test
    void getProduct() {
        assertEquals(mockProduct, billedProduct.getProduct());
    }

    @Test
    void getQuantity() {
        assertEquals(42, billedProduct.getQuantity());
    }
}