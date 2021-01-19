package product.strategy.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
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

    static Stream<Arguments> inputs() {
        return Stream.of(
                arguments(BigDecimal.valueOf(10.00), TEN, BigDecimal.valueOf(1.0)),
                arguments(BigDecimal.valueOf(10.00), FIVE, BigDecimal.valueOf(0.5)),
                arguments(BigDecimal.valueOf(14.99), TEN, BigDecimal.valueOf(1.50)),
                arguments(BigDecimal.valueOf(47.50), TEN, BigDecimal.valueOf(4.75)),
                arguments(BigDecimal.valueOf(47.50), FIVE, BigDecimal.valueOf(2.40)),
                arguments(BigDecimal.valueOf(27.99), TEN, BigDecimal.valueOf(2.80)),
                arguments(BigDecimal.valueOf(27.99), FIVE, BigDecimal.valueOf(1.40)),
                arguments(BigDecimal.valueOf(18.99), TEN, BigDecimal.valueOf(1.90)),
                arguments(BigDecimal.valueOf(11.25), FIVE, BigDecimal.valueOf(0.60))
        );
    }
}