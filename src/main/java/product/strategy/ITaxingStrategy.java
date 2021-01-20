package product.strategy;

import java.math.BigDecimal;

public interface ITaxingStrategy {

    /**
     * Evaluates the taxes over the provided amount
     *
     * @param price The price
     * @return the taxes amount
     */
    BigDecimal getTaxes(BigDecimal price);
}
