package pucmm.edu;

import lombok.*;
import pucmm.edu.exceptions.InvalidPrice;

@Getter
@Builder
public class Product {

    private String name;
    private double unitPrice;

    public Product(String name, double unitPrice) throws InvalidPrice {
        setUnitPrice(unitPrice);
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) throws InvalidPrice {
        if (unitPrice < 0.0) {
            throw new InvalidPrice();
        }
        this.unitPrice = unitPrice;
    }
}
