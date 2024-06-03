package pucmm.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pucmm.edu.exceptions.InvalidPrice;
import pucmm.edu.exceptions.InvalidProductQuantity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product;

    @BeforeEach
    public void setUp() throws InvalidPrice {
        cart = new Cart();
        product = new Product("Test Product", 10.0);
    }

    // Prueba la creación de un carrito con una lista predefinida
    @Test
    public void testConstructorWithCartItems() throws InvalidProductQuantity {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem1 = new CartItem(product, 5);
        CartItem cartItem2 = new CartItem(product, 3);
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        Cart newCart = new Cart(cartItems);

        assertEquals(2, newCart.getItems().size());
        assertTrue(newCart.getItems().contains(cartItem1));
        assertTrue(newCart.getItems().contains(cartItem2));
    }

    // Prueba agregar un elemento al carrito
    @Test
    public void testAddItem() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        cart.addItem(cartItem);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(cartItem));
    }

    // Prueba eliminar un elemento del carrito
    @Test
    public void testRemoveItem() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        cart.addItem(cartItem);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(cartItem));

        cart.removeItem(cartItem);

        assertEquals(0, cart.getItems().size());
        assertFalse(cart.getItems().contains(cartItem));
    }

    // Prueba para obtener el total con un solo elemento en el carrito
    @Test
    public void testGetTotalWithOneItem() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        cart.addItem(cartItem);

        assertEquals(50.0, cart.getTotal());
    }

    // Prueba para obtener el total con varios elementos en el carrito
    @Test
    public void testGetTotalWithMultipleItems() throws InvalidProductQuantity {
        CartItem cartItem1 = new CartItem(product, 2);
        CartItem cartItem2 = new CartItem(new Product("Another", 20.0), 3);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);

        assertEquals(80.0, cart.getTotal());
    }

    // Prueba obtener el total de un carrito vacío
    @Test
    public void testGetTotalEmptyCart() {
        assertEquals(0.0, cart.getTotal());
    }

    // Prueba que verifica el total se haya actualizado correctamente después de eliminar el elemento
    @Test
    public void testGetTotalAfterRemovingItem() throws InvalidProductQuantity {
        CartItem cartItem1 = new CartItem(product, 5);
        CartItem cartItem2 = new CartItem(new Product("Otro Producto", 20.0), 3);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);

        // Verifica que el total sea correcto después de añadir los elementos
        assertEquals(110.0, cart.getTotal());

        // Elimina un elemento del carrito
        cart.removeItem(cartItem1);

        assertEquals(60.0, cart.getTotal());
    }

    // Prueba para obtener el total con productos gratis
    @Test
    public void testGetTotalWithZeroPriceItem() throws InvalidProductQuantity {
        // Crea un producto con precio cero
        Product zeroPriceProduct = new Product("Producto de Precio Cero", 0.0);
        CartItem cartItem = new CartItem(zeroPriceProduct, 5);
        cart.addItem(cartItem);

        assertEquals(0.0, cart.getTotal());
    }

    // Prueba eliminar un elemento no existente del carrito
    @Test
    public void testRemoveNonExistingItem() throws InvalidProductQuantity {
        CartItem cartItem = new CartItem(product, 5);
        cart.addItem(cartItem);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(cartItem));

        // Intenta eliminar un elemento que no existe
        CartItem nonExistingItem = new CartItem(new Product("Non-existing Product", 5.0), 1);
        cart.removeItem(nonExistingItem);

        // Asegura que el elemento original aún esté en el carrito
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(cartItem));
    }

}
