package product.impl;

import product.ITaxableProduct;
import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TaxableProduct implements ITaxableProduct {
    private static final BigDecimal ROUNDING_FACTOR = BigDecimal.valueOf(0.05);

    private final String product;
    private final BigDecimal price;
    private final ITaxingStrategy taxingStrategy;
    private final ITaxingStrategy importTaxStrategy;

    public TaxableProduct(String product, BigDecimal price, ITaxingStrategy taxingStrategy, ITaxingStrategy importTaxStrategy) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(price);
        Objects.requireNonNull(taxingStrategy);
        Objects.requireNonNull(importTaxStrategy);
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new UnsupportedOperationException("Only positive values are valid");
        this.product = product;
        this.price = price;
        this.taxingStrategy = taxingStrategy;
        this.importTaxStrategy = importTaxStrategy;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getTaxedPrice() {
        return evaluateTaxes().add(price);
    }

    @Override
    public BigDecimal getTaxes() {
        return evaluateTaxes();
    }

    private BigDecimal evaluateTaxes() {
        return fiveCentRounding(taxingStrategy.getTaxes(price).add(importTaxStrategy.getTaxes(price)));
    }

    private BigDecimal fiveCentRounding(BigDecimal value) {
        return value
                .divide(ROUNDING_FACTOR, RoundingMode.UP)
                .setScale(0, RoundingMode.UP)
                .multiply(ROUNDING_FACTOR);
    }
}
