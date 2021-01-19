package receipt.impl;

import receipt.IReceipt;
import org.apache.commons.lang3.tuple.Triple;

import java.math.BigDecimal;
import java.util.List;

public class Receipt implements IReceipt {
    private final List<Triple<String, Integer, BigDecimal>> cartProducts;
    private final BigDecimal total;
    private final BigDecimal taxes;

    public Receipt(List<Triple<String, Integer, BigDecimal>> cartProducts, BigDecimal total, BigDecimal taxes) {
        this.cartProducts = cartProducts;
        this.total = total;
        this.taxes = taxes;
    }

    @Override
    public List<Triple<String, Integer, BigDecimal>> getCartContent() {
        return cartProducts;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public BigDecimal getTaxes() {
        return taxes;
    }
}
