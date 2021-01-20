package cart.impl;

import cart.ICart;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import receipt.IReceipt;
import product.ITaxableProduct;
import receipt.IReceiptProduct;
import receipt.impl.Receipt;
import receipt.impl.ReceiptProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart implements ICart {

    List<Pair<ITaxableProduct, Integer>> cart;
    private BigDecimal taxes;
    private BigDecimal total;

    public Cart() {
        this.cart = new ArrayList<>();
        taxes = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    @Override
    public void add(ITaxableProduct product, Integer quantity) {
        cart.add(Pair.of(product, quantity));
        BigDecimal decimalQuantity = BigDecimal.valueOf(quantity);
        taxes = taxes.add(product.getTaxes().multiply(decimalQuantity));
        total = total.add(product.getTaxedPrice().multiply(decimalQuantity));
    }

    @Override
    public IReceipt bill() {
        List<IReceiptProduct> receiptItems = cart
                .stream()
                .map(product -> ReceiptProduct.from(product.getLeft(), product.getRight()))
                .collect(Collectors.toList());
        return new Receipt(receiptItems, total, taxes);
    }
}
