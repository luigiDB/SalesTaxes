package cart.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.ITaxableProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void testThatNullInputsAreRejected() {
        assertThrows(NullPointerException.class, () -> {
            new BilledProduct(null, 10);
        });
    }

    @Test
    void testThatNegativeQuantitiesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BilledProduct(mockProduct, -10);
        });
    }
}