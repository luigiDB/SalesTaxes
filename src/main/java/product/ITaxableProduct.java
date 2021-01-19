package product;

import java.math.BigDecimal;

public interface ITaxableProduct {
    String getProduct();

    BigDecimal getPrice();

    BigDecimal getTaxedPrice();

    BigDecimal getTaxes();
}
