package pucmm.edu;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public double getTotal() {
        double totalPrice = 0.0;
        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
