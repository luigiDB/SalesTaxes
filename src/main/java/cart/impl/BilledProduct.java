package cart.impl;

import cart.IBilledProduct;
import product.ITaxableProduct;

import java.util.Objects;

public class BilledProduct implements IBilledProduct {
    private final ITaxableProduct product;
    private final int quantity;

    public BilledProduct(ITaxableProduct product, int quantity) {
        Objects.requireNonNull(product);
        if (quantity < 0)
            throw new UnsupportedOperationException("Quantity must be positive");
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public ITaxableProduct getProduct() {
        return product;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
