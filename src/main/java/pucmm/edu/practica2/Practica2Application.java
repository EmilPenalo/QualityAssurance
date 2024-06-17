package pucmm.edu.practica2;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@Theme("custom-theme")
public class Practica2Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Practica2Application.class, args);
    }

}
