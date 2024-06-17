package pucmm.edu.practica2;

import com.microsoft.playwright.Page;

// Maneja las prueba de login utilizando el formulario de login
public class LoginPO {
    private final Page page;
    public LoginPO(Page page) {
        this.page = page;
    }
    public void login(String username, String password) {
        page.fill("vaadin-text-field[name='username'] > input", username);
        page.fill("vaadin-password-field[name='password'] > input", password);
        page.click("vaadin-button");
    }
}