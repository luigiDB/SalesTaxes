package receipt.impl;

import receipt.IReceipt;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.util.List;

public class Receipt implements IReceipt {
    private final List<IReceiptProduct> cartProducts;
    private final BigDecimal total;
    private final BigDecimal taxes;

    public Receipt(List<IReceiptProduct> cartProducts, BigDecimal total, BigDecimal taxes) {
        this.cartProducts = cartProducts;
        this.total = total;
        this.taxes = taxes;
    }

    @Override
    public List<IReceiptProduct> billedItems() {
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
