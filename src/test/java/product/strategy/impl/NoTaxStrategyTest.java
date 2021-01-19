package product.strategy.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NoTaxStrategyTest {

    @ParameterizedTest
    @MethodSource("inputs")
    void testThatTheEvaluatedAreZero(BigDecimal input) {
        NoTaxStrategy taxingStrategy = new NoTaxStrategy();
        assertEquals(BigDecimal.ZERO, taxingStrategy.getTaxes(input));
    }

    static Stream<Arguments> inputs() {
        return Stream.of(
                arguments(BigDecimal.ZERO),
                arguments(BigDecimal.ONE),
                arguments(BigDecimal.ONE.negate())
        );
    }
}