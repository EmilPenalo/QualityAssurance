package pucmm.edu.practica2;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pucmm.edu.practica2.views.TodoView;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TodoTest extends PlaywrightIT{
    @BeforeEach
    void login() {
        // Login inicial
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("user", "user");

        // Redirect a raíz
        page.navigate("http://localhost:" + port + "/");
    }

    @Test
    void directAccessTest() {
        // Comprueba el acceso a la ruta
        assertThat(page).hasTitle(TodoView.pageTitle);
    }

    @Test
    void elementsExist() {
        // Existe la lista
        Locator listLocator = page.locator("#todo-list");
        assertThat(listLocator).isVisible();

        // Existe el input de texto
        Locator inputLocator = page.locator("#todo-input > input");
        assertThat(inputLocator).isVisible();

        // Existe el botón
        Locator buttonLocator = page.locator("#todo-button");
        assertThat(buttonLocator).isVisible();
    }

    // Función de ayuda para agregar nuevas entradas a la lista
    public void addToList(String item) {
        Locator inputLocator = page.locator("#todo-input > input");
        Locator buttonLocator = page.locator("#todo-button");

        inputLocator.fill(item);
        buttonLocator.click();
    }

    // Probando la adición de tareas a la lista
    @Test
    void addingToListTest() {

        // Prueba con texto genérico
        addToList("Test1");
        Locator listLocator = page.locator("#todo-list");
        Locator checkboxLocator = listLocator.locator("vaadin-checkbox > label:text('Test1')");
        assertThat(checkboxLocator).isVisible();

        // Prueba con oración compleja
        addToList("A more complex text");
        checkboxLocator = listLocator.locator("vaadin-checkbox > label:text('A more complex text')");
        assertThat(checkboxLocator).isVisible();

        // Prueba con un solo carácter
        addToList("a");
        checkboxLocator = listLocator.locator("vaadin-checkbox > label:text('a')");
        assertThat(checkboxLocator).isVisible();

        // Comprobando que no todos los textos son encontrados
        checkboxLocator = listLocator.locator("vaadin-checkbox > label:text('b')");
        assertThat(checkboxLocator).not().isVisible();
    }

    // Comprueba que no se pueden agregar tareas vacías
    @Test
    void addEmptyItem() {
        addToList("");

        Locator listLocator = page.locator("#todo-list");

        // Revisa que no exista ningún item en la lista
        Locator checkboxLocator = listLocator.locator("vaadin-checkbox > label");
        assertThat(checkboxLocator).not().isVisible();
    }

}
