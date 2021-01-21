package product.strategy.impl;

import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;

public class RoundedPercentageStrategy implements ITaxingStrategy {

    private final BigDecimal taxation;

    public RoundedPercentageStrategy(BigDecimal percentage) {
        taxation = percentage.scaleByPowerOfTen(-2);
    }

    @Override
    public BigDecimal getTaxes(BigDecimal price) {
        return price.multiply(taxation);
    }
}
