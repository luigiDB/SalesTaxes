package cart.impl;

import cart.ICart;
import org.apache.commons.lang3.tuple.Pair;
import product.ITaxableProduct;
import receipt.IReceipt;
import receipt.impl.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements ICart {

    private final List<Pair<ITaxableProduct, Integer>> cart;
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
        return new Receipt(cart, total, taxes);
    }
}
