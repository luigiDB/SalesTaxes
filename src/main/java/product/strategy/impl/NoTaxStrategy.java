package product.strategy.impl;

import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;

public class NoTaxStrategy implements ITaxingStrategy {
    @Override
    public BigDecimal getTaxes(BigDecimal price) {
        return BigDecimal.ZERO;
    }
}
