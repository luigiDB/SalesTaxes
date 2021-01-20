package cart.impl;

import cart.IBilledProduct;
import product.ITaxableProduct;

public class BilledProduct implements IBilledProduct {
    private final ITaxableProduct product;
    private final int quantity;

    public BilledProduct(ITaxableProduct product, int quantity) {
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
