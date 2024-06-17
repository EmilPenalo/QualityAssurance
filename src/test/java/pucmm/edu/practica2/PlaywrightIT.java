package pucmm.edu.practica2;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


// Formato general de pruebas de playwright
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("playwright")
public class PlaywrightIT {

    // Se utiliza un puerto aleatorio para evitar conflictos con la aplicación normal
    @LocalServerPort
    protected int port;

    // Variables estáticas para Playwright y el navegador
    private static Playwright playwright;
    private static Browser browser;

    // Variables de instancia para la página y el contexto del navegador
    protected Page page;
    private BrowserContext browserContext;

    // Método estático que se ejecuta una vez antes de todas las pruebas
    // Inicia Playwright y abre el navegador en modo no headless
    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = false;
        browser = browserType.launch(launchOptions);
    }

    // Método estático que se ejecuta una vez después de todas las pruebas
    // Cierra el navegador y Playwright
    @AfterAll
    static void afterAll() {
        browser.close();
        playwright.close();
    }

    // Método que se ejecuta antes de cada prueba
    // Crea un nuevo contexto de navegador y abre una nueva página
    @BeforeEach
    void beforeEach() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    // Método que se ejecuta después de cada prueba
    // Cierra la página y el contexto del navegador
    @AfterEach
    void afterEach() {
        page.close();
        browserContext.close();
    }
}