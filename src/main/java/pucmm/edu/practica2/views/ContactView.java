package pucmm.edu.practica2.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import pucmm.edu.practica2.entities.Contact;

@Route(value = "contact", layout = MainLayout.class)
@PageTitle(ContactView.pageTitle)
@RolesAllowed("USER")
public class ContactView extends VerticalLayout {

    public static final String pageTitle = "Contact Us | Practica 2";
    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

    public ContactView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        // Contenedor del formulario
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setAlignItems(Alignment.CENTER);
        flexLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setMinWidth(100, Unit.PERCENTAGE);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);

        // Campo de texto para el nombre
        TextField nameField = new TextField("Name");
        nameField.setId("contact-name");
        nameField.setRequiredIndicatorVisible(true);
        nameField.setWidth(100, Unit.PERCENTAGE);
        nameField.setMaxWidth(25, Unit.EM);

        // Validador aceptando un mínimo de tres carácteres
        binder.forField(nameField)
            .asRequired("A name is required")
            .withValidator(
                    name -> name.length() >= 3,
                    "Name must contain at least three characters")
            .bind(Contact::getName, Contact::setName);
        
        // Campo de texto para el correo
        EmailField emailField = new EmailField("Email");
        emailField.setId("contact-email");
        emailField.setRequiredIndicatorVisible(true);
        emailField.setMaxWidth(25, Unit.EM);
        emailField.setWidth(100, Unit.PERCENTAGE);

        // Validador para que el email sea requerido y tenga formato de email
        binder.forField(emailField)
                .asRequired("An email is required")
                .withValidator(new EmailValidator(
                        "This doesn't look like a valid email address"))
                .bind(Contact::getEmail, Contact::setEmail);

        // Campo de texto para el mensaje
        TextArea messageField = new TextArea("Message");
        messageField.setId("contact-message");
        messageField.setRequiredIndicatorVisible(true);
        messageField.setMaxWidth(25, Unit.EM);
        messageField.setWidth(100, Unit.PERCENTAGE);

        // Validador para que el mensaje sea requerido
        binder.forField(messageField)
                .asRequired("A message is required")
                .bind(Contact::getMessage, Contact::setMessage);

        // Botón para enviar el formulario
        Button sendButton = new Button("Enviar");
        sendButton.setId("contact-submit");
        sendButton.addClassName("mt-m");

        sendButton.addClickListener(event -> {
            if (binder.validate().isOk()) {
                // Lógica para procesar el formulario

                // Limpia los inputs
                nameField.clear();
                emailField.clear();
                messageField.clear();

                // Esconde el formulario
                flexLayout.setVisible(false);

                // Muestra el mensaje de envío válido
                H3 success = new H3("Message sent successfully");
                success.setId("success-text");
                add(success);
            }
        });

        // Añadir campos y botón al layout
        flexLayout.add(new H1("Contact Us"), nameField, emailField, messageField, sendButton);
        add(flexLayout);
    }
}