package product.strategy.impl;

import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundedPercentageStrategy implements ITaxingStrategy {

    private static final BigDecimal ROUNDING_FACTOR = BigDecimal.valueOf(0.05);

    private final BigDecimal taxation;

    public RoundedPercentageStrategy(BigDecimal percentage) {
        taxation = percentage.scaleByPowerOfTen(-2);
    }

    @Override
    public BigDecimal getTaxes(BigDecimal price) {
        return fiveCentRounding(price.multiply(taxation));
    }

    private BigDecimal fiveCentRounding(BigDecimal value) {
        return value
                .divide(ROUNDING_FACTOR, RoundingMode.UP)
                .setScale(0, RoundingMode.UP)
                .multiply(ROUNDING_FACTOR);
    }
}
