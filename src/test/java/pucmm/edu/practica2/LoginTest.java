package pucmm.edu.practica2;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import pucmm.edu.practica2.views.LoginView;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends PlaywrightIT {

    @Test
    void directAccessTest() {
        // Comprueba el acceso a login
        page.navigate("http://localhost:" + port + "/login");
        assertTrue(page.url().contains("/login"));
        assertThat(page).hasTitle(LoginView.pageTitle);
    }

    @Test
    void redirectToLogin() {
        // Prueba de redirección devuelta a login al entrar a /contact
        page.navigate("http://localhost:" + port + "/contact");
        assertTrue(page.url().contains("/login"));
        assertThat(page).hasTitle(LoginView.pageTitle);

        // Prueba de redirección devuelta a login al entrar a la ruta raíz
        page.navigate("http://localhost:" + port + "/");
        assertTrue(page.url().contains("/login"));
        assertThat(page).hasTitle(LoginView.pageTitle);
    }

    @Test
    void locateForm() {
        page.navigate("http://localhost:" + port + "/login");

        // Comprobación que el formulario existe
        Locator formLocator = page.locator("#login-form");
        assertThat(formLocator).isVisible();
    }

    @Test
    void locateFormFields() {
        page.navigate("http://localhost:" + port + "/login");

        // Comprobación que el input para el usuario existe
        Locator usernameFieldLocator = page.locator("vaadin-text-field[name='username'] > input");
        assertThat(usernameFieldLocator).isVisible();

        // Comprobación que el input para el password existe
        Locator passwordFieldLocator = page.locator("vaadin-password-field[name='password'] > input");
        assertThat(passwordFieldLocator).isVisible();

        // Comprobación que el botón de entrega del formulario de login existe
        Locator submitButtonLocator = page.locator("vaadin-button[slot='submit']");
        assertThat(submitButtonLocator).isVisible();
    }

    @Test
    void validLogin() {
        // Testing para el usuario #1
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("user", "user");

        assertFalse(page.url().contains("/login"));
        assertThat(page).not().hasTitle(LoginView.pageTitle);

        // Testing para el usuario #2
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("admin", "admin");

        assertFalse(page.url().contains("/login"));
        assertThat(page).not().hasTitle(LoginView.pageTitle);
    }

    @Test
    void invalidLogin() {
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("invalid", "login");

        // Verificación de que se mantiene en la página de login
        assertTrue(page.url().contains("/login"));
        assertThat(page).hasTitle(LoginView.pageTitle);
    }

    @Test
    void logoutTest() {
        // Login del usuario
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("user", "user");

        assertFalse(page.url().contains("/login"));

        // Logout
        page.click("#logout-button");
        page.waitForURL("http://localhost:" + port + "/login");

        // Intento de acceso a página protegida
        page.navigate("http://localhost:" + port + "/contact");

        // Comprobando la redirección a login
        assertTrue(page.url().contains("/login"));
        assertThat(page).hasTitle(LoginView.pageTitle);
    }
}
