package cart;

import product.ITaxableProduct;

public interface IBilledProduct {
    /**
     * Return the product
     *
     * @return product
     */
    ITaxableProduct getProduct();

    /**
     * Return the lots of the product
     *
     * @return the number of lots
     */
    int getQuantity();
}
