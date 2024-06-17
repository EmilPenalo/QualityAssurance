package pucmm.edu.practica2;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pucmm.edu.practica2.views.ContactView;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest extends PlaywrightIT {

    @BeforeEach
    void login() {
        // Login inicial
        page.navigate("http://localhost:" + port + "/login");
        new LoginPO(page).login("user", "user");

        // Redirect a /contact
        page.navigate("http://localhost:" + port + "/contact");
    }

    @Test
    void directAccessTest() {
        // Comprueba el acceso a contact
        assertTrue(page.url().contains("/contact"));
        assertThat(page).hasTitle(ContactView.pageTitle);
    }

    @Test
    void locateFormFields() {
        // Comprobación que el input para el nombre existe
        Locator nameLocator = page.locator("#contact-name > input");
        assertThat(nameLocator).isVisible();

        // Comprobación que el input para el emails existe
        Locator emailLocator = page.locator("#contact-email > input");
        assertThat(emailLocator).isVisible();

        // Comprobación que el input para mensaje existe
        Locator messageLocator = page.locator("#contact-message > textarea");
        assertThat(messageLocator).isVisible();

        // Comprobación que el input para mensaje existe
        Locator buttonLocator = page.locator("#contact-submit");
        assertThat(buttonLocator).isVisible();
    }

    // Función de ayuda para llenar el formulario de contacto
    public void fillContactForm(String name, String email, String message) {
        Locator nameLocator = page.locator("#contact-name > input");
        nameLocator.fill(name);

        Locator emailLocator = page.locator("#contact-email > input");
        emailLocator.fill(email);

        Locator messageLocator = page.locator("#contact-message > textarea");
        messageLocator.fill(message);

        Locator buttonLocator = page.locator("#contact-submit");
        buttonLocator.click();
    }

    // Comprobando que el formulario puede ser entregado y se visualiza un mensaje
    @Test
    void submitForm() {
        fillContactForm("nombre", "mail@mail.com", "mensaje");
        Locator successLocator = page.locator("success-text");
        successLocator.isVisible();
    }

    // Comprobando que el nombre tenga un mínimo de 3 chars
    @Test
    void invalidName() {
        fillContactForm("", "mail@mail.com", "mensaje");
        Locator successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("a", "mail@mail.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("ab", "mail@mail.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("abc", "mail@mail.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isVisible();
    }

    // Comprobando que el email sea valido
    @Test
    void invalidEmail() {
        fillContactForm("nombre", "", "mensaje");
        Locator successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("nombre", "mail", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("nombre", "mail.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("nombre", "a.net.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("nombre", "@mail.com", "mensaje");
        successLocator = page.locator("success-text");
        successLocator.isHidden();
    }

    // Comprobando que un mensaje sea requerido
    @Test
    void invalidMessage() {
        fillContactForm("nombre", "mail@mail.com", "");
        Locator successLocator = page.locator("success-text");
        successLocator.isHidden();

        fillContactForm("nombre", "mail", "a");
        successLocator = page.locator("success-text");
        successLocator.isVisible();
    }

}
