package product.impl;

import org.junit.jupiter.api.Test;
import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaxableProductTest {

    private static final String PRODUCT_NAME = "productName";
    private static final BigDecimal TWO = BigDecimal.valueOf(2L);
    private final ITaxingStrategy voidStrategy = (value) -> BigDecimal.ZERO;
    private TaxableProduct taxableProduct;

    @Test
    void testThatTheProductReferenceIsStored() {
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, voidStrategy, voidStrategy);
        assertEquals(PRODUCT_NAME, taxableProduct.getProduct());
    }

    @Test
    void testThatThePriceIsStored() {
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, voidStrategy, voidStrategy);
        assertEquals(BigDecimal.TEN, taxableProduct.getPrice());
    }

    @Test
    void testThatTheTaxesAreEvaluatedAccordingToTheStrategies() {
        ITaxingStrategy taxingStrategy = (value) -> BigDecimal.ONE;
        ITaxingStrategy importTaxStrategy = (value) -> BigDecimal.ONE;
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, taxingStrategy, importTaxStrategy);

        assertEquals(TWO, taxableProduct.getTaxes());
    }

    @Test
    void testThatTheTaxedPriceIsEvaluatedAccordingToTheStrategies() {
        ITaxingStrategy taxingStrategy = (value) -> BigDecimal.ONE;
        ITaxingStrategy importTaxStrategy = (value) -> BigDecimal.ONE;
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, taxingStrategy, importTaxStrategy);

        assertEquals(BigDecimal.valueOf(12L), taxableProduct.getTaxedPrice());
    }
}