package receipt;

import java.math.BigDecimal;
import java.util.List;

public interface IReceipt {

    /**
     * Return a list of the products in the receipt with the quantity and the full price
     * @return a list of items
     */
    List<IReceiptProduct> billedItems();

    /**
     * Evaluates the receipt total
     * @return The total price
     */
    BigDecimal getTotal();

    /**
     * Evaluates the taxes payed in the receipt
     * @return The total taxes amount
     */
    BigDecimal getTaxes();
}
