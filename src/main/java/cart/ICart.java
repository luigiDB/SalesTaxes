package cart;

import product.ITaxableProduct;
import receipt.IReceipt;

public interface ICart {

    void add(ITaxableProduct product , Integer quantity);

    IReceipt bill();
}
