package pucmm.edu;

import junit.framework.TestCase;
import pucmm.edu.exceptions.InvalidPrice;

import static org.junit.Assert.assertThrows;

public class ProductTest extends TestCase {

    // Pruebas del constructor del producto con entradas válidas
    public void testProductConstructorValid() throws InvalidPrice {
        // Probando con precio decimal
        Product productValid = new Product("ValidProduct", 12.99);
        assertEquals("ValidProduct", productValid.getName());
        assertEquals(12.99, productValid.getUnitPrice());

        // Probando con un precio entero
        productValid = new Product("aProduct", 10);
        assertEquals("aProduct", productValid.getName());
        assertEquals(10.0, productValid.getUnitPrice());

        // Probando con un precio cero
        productValid = new Product("Free", 0);
        assertEquals(0.0, productValid.getUnitPrice());
    }

    // Pruebas del constructor del producto con entradas inválidas
    public void testProductConstructorExceptions() {
        // Probando con un nombre nulo
        assertThrows(IllegalArgumentException.class, () -> new Product(null, 9.99));

        // Probando con un nombre vacío
        assertThrows(IllegalArgumentException.class, () -> new Product("", 9.99));

        // Probando con precios negativos
        assertThrows(InvalidPrice.class, () -> new Product("valid", -1.5));
        assertThrows(InvalidPrice.class, () -> new Product("valid", -0.001));
        assertThrows(InvalidPrice.class, () -> new Product("valid", -105.55));
    }

    // Pruebas para establecer un precio válido
    public void testSetProductPrice() throws InvalidPrice {
        Product product = new Product("test", 9.99);

        // Estableciendo un nuevo precio válido
        product.setUnitPrice(200.55);
        assertEquals(200.55, product.getUnitPrice());

        // Estableciendo otro nuevo precio válido
        product.setUnitPrice(1.25);
        assertEquals(1.25, product.getUnitPrice());
    }

    // Pruebas para establecer un precio inválido
    public void testInvalidSetProductPrice() throws InvalidPrice {
        Product product = new Product("Testing", 0);

        // Intentando establecer un precio negativo
        assertThrows(InvalidPrice.class, () -> product.setUnitPrice(-15));
        assertThrows(InvalidPrice.class, () -> product.setUnitPrice(-0.15));
    }

    // Pruebas para establecer un nombre válido
    public void testSetProductName() throws InvalidPrice {
        Product product = new Product("test", 9.99);

        // Estableciendo un nuevo nombre válido
        product.setName("valid name");
        assertEquals("valid name", product.getName());

        // Estableciendo otro nuevo nombre válido
        product.setName("Español");
        assertEquals("Español", product.getName());
    }

    // Pruebas para establecer un nombre inválido
    public void testInvalidSetProductName() throws InvalidPrice {
        Product product = new Product("Testing", 0);

        // Intentando establecer un nombre nulo
        assertThrows(IllegalArgumentException.class, () -> product.setName(null));

        // Intentando establecer un nombre vacío
        assertThrows(IllegalArgumentException.class, () -> product.setName(""));
    }

    // Prueba adicional para establecer un nombre válido después de un intento inválido
    public void testSetValidNameAfterInvalid() throws InvalidPrice {
        Product product = new Product("test", 9.99);

        // Intentando establecer un nombre inválido primero
        assertThrows(IllegalArgumentException.class, () -> product.setName(""));

        // Estableciendo un nombre válido después del intento inválido
        product.setName("ValidName");
        assertEquals("ValidName", product.getName());
    }

    // Pruebas adicionales para casos borde y excepciones
    public void testEdgeCases() throws InvalidPrice {
        // Probando con el precio máximo permitido
        Product product = new Product("ExpensiveProduct", Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, product.getUnitPrice());

        // Probando el límite de dobles
        product.setUnitPrice(Double.MAX_VALUE + 1);
        assertEquals(Double.MAX_VALUE, product.getUnitPrice());

        // Probando estableciendo un nombre largo
        String longName = "a".repeat(10000);
        product.setName(longName);
        assertEquals(longName, product.getName());

        // Probando con un nombre de un solo carácter
        product.setName("b");
        assertEquals("b", product.getName());
    }
}
