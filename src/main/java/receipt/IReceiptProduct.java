package receipt;

import java.math.BigDecimal;

public interface IReceiptProduct {

    /**
     * Get the product identifier
     *
     * @return product identifier
     */
    String getProduct();

    /**
     * Get the quantity of the product
     *
     * @return quantity
     */
    int getQuantity();

    /**
     * Get the price associated to the products (considering quantity).
     *
     * @return the stock product price
     */
    BigDecimal getPrice();
}
