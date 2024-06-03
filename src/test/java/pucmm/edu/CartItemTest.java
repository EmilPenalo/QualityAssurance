package pucmm.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pucmm.edu.exceptions.InvalidPrice;
import pucmm.edu.exceptions.InvalidProductQuantity;

import static org.junit.jupiter.api.Assertions.*;

// Para el uso de @BeforeEach necesitamos utilizarán JUnit 4, por lo cual no se utiliza TestCase y se opta por usar Junit Jupiter API
public class CartItemTest {
    private Product product;

    // Configuración antes de cada prueba
    @BeforeEach
    public void setUp() throws InvalidPrice {
        product = new Product("Test Product", 10.0);
    }

    // Prueba del constructor de CartItem con cantidad válida
    @Test
    public void testCartItemConstructorValid() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        assertEquals(product, cartItem.getProduct());
        assertEquals(5, cartItem.getQuantity());

        // Probando con la cantidad max establecida
        cartItem = new CartItem(product, CartItem.MAX_QUANTITY);
        assertEquals(CartItem.MAX_QUANTITY, cartItem.getQuantity());
    }

    // Prueba del constructor con cantidades inválidas
    @Test
    public void testCartItemConstructorInvalidQuantity() {
        assertThrows(InvalidProductQuantity.class, () -> new CartItem(product, 0));
        assertThrows(InvalidProductQuantity.class, () -> new CartItem(product, -1));
        assertThrows(InvalidProductQuantity.class, () -> new CartItem(product, 1000));
    }

    // Prueba del método getTotalPrice
    @Test
    public void testGetTotalPrice() throws InvalidProductQuantity {
        // Probando caso regular
        CartItem cartItem = new CartItem(product, 5);
        assertEquals(50.0, cartItem.getTotalPrice());

        // Probando con cantidad 1
        cartItem = new CartItem(product, 1);
        assertEquals(10.0, cartItem.getTotalPrice());

        // Probando con precio cero
        Product testProduct = new Product("test", 0);
        cartItem = new CartItem(testProduct, 10);
        assertEquals(0.0, cartItem.getTotalPrice());

        // Probando caso con decimales
        testProduct.setUnitPrice(0.25);
        cartItem = new CartItem(testProduct, 4);
        assertEquals(1.0, cartItem.getTotalPrice());
    }

    // Prueba del método setQuantity con cantidad válida
    @Test
    public void testSetQuantityValid() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        cartItem.setQuantity(10);
        assertEquals(10, cartItem.getQuantity());

        // Probando con la cantidad maxima establecida
        cartItem.setQuantity(CartItem.MAX_QUANTITY);
        assertEquals(CartItem.MAX_QUANTITY, cartItem.getQuantity());
    }

    // Prueba del método setQuantity con cantidades inválidas
    @Test
    public void testSetQuantityInvalid() {
        CartItem cartItem = null;
        try {
            cartItem = new CartItem(product, 5);
        } catch (InvalidProductQuantity | InvalidPrice e) {
            fail("Exception should not be thrown");
        }
        CartItem finalCartItem = cartItem;

        assertThrows(InvalidProductQuantity.class, () -> finalCartItem.setQuantity(0));
        assertThrows(InvalidProductQuantity.class, () -> finalCartItem.setQuantity(-1));

        assertThrows(InvalidProductQuantity.class, () -> finalCartItem.setQuantity(CartItem.MAX_QUANTITY + 1));
        assertThrows(InvalidProductQuantity.class, () -> finalCartItem.setQuantity(Integer.MAX_VALUE));
    }
}
