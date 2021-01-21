package product.strategy.impl;

import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public class RoundedPercentageStrategy implements ITaxingStrategy {

    private final BigDecimal taxation;

    public RoundedPercentageStrategy(BigDecimal percentage) {
        Objects.requireNonNull(percentage);
        if(percentage.compareTo(BigDecimal.ZERO) < 0 )
            throw new UnsupportedOperationException("Only positive values are valid");
        taxation = percentage.scaleByPowerOfTen(-2);
    }

    @Override
    public BigDecimal getTaxes(BigDecimal price) {
        Objects.requireNonNull(price);
        if(price.compareTo(BigDecimal.ZERO) < 0 )
            throw new UnsupportedOperationException("Only positive values are valid");
        return price.multiply(taxation);
    }
}
