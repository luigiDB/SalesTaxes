package product.strategy;

import java.math.BigDecimal;

public interface ITaxingStrategy {

    BigDecimal getTaxes(BigDecimal price);
}
