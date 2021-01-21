package receipt.impl;

import cart.IBilledProduct;
import receipt.IReceipt;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Receipt implements IReceipt {
    private final List<IReceiptProduct> cartProducts;
    private final BigDecimal total;
    private final BigDecimal taxes;

    public Receipt(List<IBilledProduct> items, BigDecimal total, BigDecimal taxes) {
        Objects.requireNonNull(items);
        Objects.requireNonNull(total);
        Objects.requireNonNull(taxes);
        this.cartProducts = items
                .stream()
                .map(ReceiptProduct::from)
                .collect(Collectors.toList());
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
