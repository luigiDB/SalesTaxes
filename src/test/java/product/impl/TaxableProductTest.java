package product.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class TaxableProductTest {

    private static final String PRODUCT_NAME = "productName";
    private static final BigDecimal TWO = BigDecimal.valueOf(2.00);
    private static final ITaxingStrategy voidStrategy = (value) -> BigDecimal.ZERO;
    private TaxableProduct taxableProduct;

    @Test
    void testThatTheProductReferenceIsStored() {
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, voidStrategy, voidStrategy);
        assertEquals(PRODUCT_NAME, taxableProduct.getProduct());
    }

    @Test
    void testThatThePriceIsStored() {
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, voidStrategy, voidStrategy);
        assertThat(BigDecimal.TEN, comparesEqualTo(taxableProduct.getPrice()));
    }

    @Test
    void testThatTheTaxesAreEvaluatedAccordingToTheStrategies() {
        ITaxingStrategy taxingStrategy = (value) -> BigDecimal.ONE;
        ITaxingStrategy importTaxStrategy = (value) -> BigDecimal.ONE;
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, taxingStrategy, importTaxStrategy);
        assertThat(TWO, comparesEqualTo(taxableProduct.getTaxes()));
    }

    @Test
    void testThatTheTaxedPriceIsEvaluatedAccordingToTheStrategies() {
        ITaxingStrategy taxingStrategy = (value) -> BigDecimal.ONE;
        ITaxingStrategy importTaxStrategy = (value) -> BigDecimal.ONE;
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, taxingStrategy, importTaxStrategy);
        assertThat(BigDecimal.valueOf(12L), comparesEqualTo(taxableProduct.getTaxedPrice()));
    }

    @ParameterizedTest
    @MethodSource("testTaxes")
    void testThatTheTaxRoundingIsCorrect(BigDecimal price, BigDecimal expectedResult) {
        taxableProduct = new TaxableProduct(PRODUCT_NAME, BigDecimal.ZERO, (value) -> price, voidStrategy);
        assertThat(expectedResult, comparesEqualTo(taxableProduct.getTaxes()));
    }

    @Test
    void testThatNullInputsAreRejected() {
        assertThrows(NullPointerException.class, () -> {
            new TaxableProduct(null, BigDecimal.TEN, voidStrategy, voidStrategy);
        });
        assertThrows(NullPointerException.class, () -> {
            new TaxableProduct(PRODUCT_NAME, null, voidStrategy, voidStrategy);
        });
        assertThrows(NullPointerException.class, () -> {
            new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, null, voidStrategy);
        });
        assertThrows(NullPointerException.class, () -> {
            new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN, voidStrategy, null);
        });
    }

    @Test
    void testThatNegativePricesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaxableProduct(PRODUCT_NAME, BigDecimal.TEN.negate(), voidStrategy, voidStrategy);
        });
    }

    static Stream<Arguments> testTaxes() {
        return Stream.of(
                arguments(BigDecimal.valueOf(1.00), BigDecimal.valueOf(1.0)),
                arguments(BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.5)),
                arguments(BigDecimal.valueOf(1.4990), BigDecimal.valueOf(1.50)),
                arguments(BigDecimal.valueOf(4.75), BigDecimal.valueOf(4.75)),
                arguments(BigDecimal.valueOf(2.375), BigDecimal.valueOf(2.40)),
                arguments(BigDecimal.valueOf(2.7990), BigDecimal.valueOf(2.80)),
                arguments(BigDecimal.valueOf(1.3995), BigDecimal.valueOf(1.40)),
                arguments(BigDecimal.valueOf(1.8990), BigDecimal.valueOf(1.90)),
                arguments(BigDecimal.valueOf(0.5625), BigDecimal.valueOf(0.60))
        );
    }
}