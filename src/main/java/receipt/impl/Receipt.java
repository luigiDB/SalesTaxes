package receipt.impl;

import org.apache.commons.lang3.tuple.Pair;
import product.ITaxableProduct;
import receipt.IReceipt;
import receipt.IReceiptProduct;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt implements IReceipt {
    private final List<IReceiptProduct> cartProducts;
    private final BigDecimal total;
    private final BigDecimal taxes;

    public Receipt(List<Pair<ITaxableProduct, Integer>> cart, BigDecimal total, BigDecimal taxes) {
        this.cartProducts = cart
                .stream()
                .map(product -> ReceiptProduct.from(product.getLeft(), product.getRight()))
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
