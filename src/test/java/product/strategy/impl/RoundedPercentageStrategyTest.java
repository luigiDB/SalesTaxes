package product.strategy.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RoundedPercentageStrategyTest {

    private static final BigDecimal TEN = BigDecimal.TEN;
    private static final BigDecimal FIVE = BigDecimal.valueOf(5L);

    @ParameterizedTest
    @MethodSource("inputs")
    void testThatTheEvaluatedAreZero(BigDecimal price, BigDecimal taxingPercentage, BigDecimal expectedResult) {
        RoundedPercentageStrategy taxingStrategy = new RoundedPercentageStrategy(taxingPercentage);
        assertThat(expectedResult, comparesEqualTo(taxingStrategy.getTaxes(price)));
    }

    @Test
    void testThatConstructorNullInputsAreRejected() {
        assertThrows(NullPointerException.class, () -> {
            new RoundedPercentageStrategy(null);
        });
    }

    @Test
    void testThatConstructorNegativeInputsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RoundedPercentageStrategy(TEN.negate());
        });
    }

    @Test
    void testThatTaxesOnNullPricesAreRejected() {
        RoundedPercentageStrategy taxingStrategy = new RoundedPercentageStrategy(TEN);
        assertThrows(NullPointerException.class, () -> {
            taxingStrategy.getTaxes(null);
        });
    }

    @Test
    void testThatTaxesOnNegativePricesAreRejected() {
        RoundedPercentageStrategy taxingStrategy = new RoundedPercentageStrategy(TEN);
        assertThrows(IllegalArgumentException.class, () -> {
            taxingStrategy.getTaxes(TEN.negate());
        });
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                arguments(BigDecimal.valueOf(10.00), TEN, BigDecimal.valueOf(1.0)),
                arguments(BigDecimal.valueOf(10.00), FIVE, BigDecimal.valueOf(0.5)),
                arguments(BigDecimal.valueOf(14.99), TEN, BigDecimal.valueOf(1.4990)),
                arguments(BigDecimal.valueOf(47.50), TEN, BigDecimal.valueOf(4.75)),
                arguments(BigDecimal.valueOf(47.50), FIVE, BigDecimal.valueOf(2.375)),
                arguments(BigDecimal.valueOf(27.99), TEN, BigDecimal.valueOf(2.7990)),
                arguments(BigDecimal.valueOf(27.99), FIVE, BigDecimal.valueOf(1.3995)),
                arguments(BigDecimal.valueOf(18.99), TEN, BigDecimal.valueOf(1.8990)),
                arguments(BigDecimal.valueOf(11.25), FIVE, BigDecimal.valueOf(0.5625))
        );
    }
}