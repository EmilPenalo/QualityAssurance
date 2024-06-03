package pucmm.edu;

import lombok.*;
import pucmm.edu.exceptions.InvalidProductQuantity;

@Getter
@Setter
public class CartItem {

    public static final int MAX_QUANTITY = 999;

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) throws InvalidProductQuantity {
        this.product = product;
        setQuantity(quantity);
    }

    public double getTotalPrice() {
        return product.getUnitPrice() * quantity;
    }

    public void setQuantity(int quantity) throws InvalidProductQuantity {
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new InvalidProductQuantity();
        }
        this.quantity = quantity;
    }
}
