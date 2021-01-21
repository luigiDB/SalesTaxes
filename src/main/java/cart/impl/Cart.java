package cart.impl;

import cart.IBilledProduct;
import cart.ICart;
import product.ITaxableProduct;
import receipt.IReceipt;
import receipt.impl.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart implements ICart {

    private final List<IBilledProduct> cart;
    private BigDecimal taxes;
    private BigDecimal total;

    public Cart() {
        cart = new ArrayList<>();
        taxes = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    @Override
    public void add(ITaxableProduct product, int quantity) {
        Objects.requireNonNull(product);
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity must be positive");
        cart.add(new BilledProduct(product, quantity));
        BigDecimal decimalQuantity = BigDecimal.valueOf(quantity);
        taxes = taxes.add(product.getTaxes().multiply(decimalQuantity));
        total = total.add(product.getTaxedPrice().multiply(decimalQuantity));
    }

    @Override
    public IReceipt bill() {
        return new Receipt(cart, total, taxes);
    }
}
