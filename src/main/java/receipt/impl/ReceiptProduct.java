package receipt.impl;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import product.ITaxableProduct;
import receipt.IReceiptProduct;

import java.math.BigDecimal;

public class ReceiptProduct implements IReceiptProduct {

    private String product;
    private int quantity;
    private BigDecimal price;

    public static IReceiptProduct from(ITaxableProduct product, int quantity) {
        return new ReceiptProduct(product.getProduct(),
                quantity,
                product.getTaxedPrice().multiply(BigDecimal.valueOf(quantity)));
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

        return new EqualsBuilder().append(quantity, that.quantity).append(product, that.product).append(price, that.price).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(product).append(quantity).append(price).toHashCode();
    }
}
