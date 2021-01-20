package product;

import java.math.BigDecimal;

public interface ITaxableProduct {
    /**
     * Return the product identifier
     * @return the product identifier
     */
    String getProduct();

    /**
     * Return the product price
     * @return the product price
     */
    BigDecimal getPrice();

    /**
     * Return the product price comprised with taxes
     * @return the full product price
     */
    BigDecimal getTaxedPrice();

    /**
     * Return the taxes needed for the product
     * @return the taxes amount for the product
     */
    BigDecimal getTaxes();
}
