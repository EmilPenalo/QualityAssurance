package pucmm.edu.practica2.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pucmm.edu.practica2.views.LoginView;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    setLoginView(http, LoginView.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
    web.ignoring().requestMatchers(new AntPathRequestMatcher("/static/**"));
  }

  @Bean
  public UserDetailsManager userDetailsService() {
    // Configurando usuarios hard-coded por el momento
    return new InMemoryUserDetailsManager(
            // El prefijo {noop} le indica a spring que la contraseña no está encriptada
            // Al tener spring security activo, se require darle mínimo un rol a los usuarios. Si no se le da un rol, no se le permite acceder a vistas protegidas incluso si no se incluye @RolesAllowed.

            User.withUsername("user").password("{noop}user").roles("USER").build(),
            User.withUsername("admin").password("{noop}admin").roles("USER").build()
    );
  }
}