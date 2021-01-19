package product.impl;

import product.ITaxableProduct;
import product.strategy.ITaxingStrategy;

import java.math.BigDecimal;

public class TaxableProduct implements ITaxableProduct {

    private String product;
    private BigDecimal price;
    private ITaxingStrategy taxingStrategy;
    private ITaxingStrategy importTaxStrategy;

    public TaxableProduct(String product, BigDecimal price, ITaxingStrategy taxingStrategy, ITaxingStrategy importTaxStrategy) {
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
        return taxingStrategy.getTaxes(price).add(importTaxStrategy.getTaxes(price));
    }
}
