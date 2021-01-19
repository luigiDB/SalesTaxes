package receipt;

import org.apache.commons.lang3.tuple.Triple;

import java.math.BigDecimal;
import java.util.List;

public interface IReceipt {

    List<Triple<String, Integer, BigDecimal>> getCartContent();

    BigDecimal getTotal();

    BigDecimal getTaxes();
}
