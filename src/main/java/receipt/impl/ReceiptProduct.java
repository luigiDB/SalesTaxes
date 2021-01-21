package receipt.impl;

import cart.IBilledProduct;
import receipt.IReceiptProduct;

import java.math.BigDecimal;

public class ReceiptProduct implements IReceiptProduct {

    private final String product;
    private final int quantity;
    private final BigDecimal price;

    public static IReceiptProduct from(IBilledProduct product) {
        return new ReceiptProduct(product.getProduct().getProduct(),
                product.getQuantity(),
                product.getProduct().getTaxedPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
    }

    public ReceiptProduct(String product, int quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptProduct that = (ReceiptProduct) o;

        if (quantity != that.quantity) return false;
        if (!product.equals(that.product)) return false;
        return price.compareTo(that.price) == 0;
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + price.setScale(2).hashCode();
        return result;
    }
}
