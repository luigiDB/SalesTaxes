package cart;

import product.ITaxableProduct;
import receipt.IReceipt;

public interface ICart {

    /**
     * Add a product to the cart
     *
     * @param product  a product instance
     * @param quantity the quantity of the product
     */
    void add(ITaxableProduct product, Integer quantity);

    /**
     * return the cart receipt
     *
     * @return an {@link IReceipt}
     */
    IReceipt bill();
}
