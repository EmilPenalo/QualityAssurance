package pucmm.edu.practica2.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "/", layout = MainLayout.class)
@RolesAllowed("USER")
@PageTitle(TodoView.pageTitle)
public class TodoView extends VerticalLayout {

    public static final String pageTitle = "To-Do | Practica 2";
    
    public TodoView() {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Lista de tareas
        var todosList = new VerticalLayout();
        todosList.setId("todo-list");
        todosList.setAlignItems(Alignment.CENTER);

        // Entrada de texto para definir las tareas
        var taskField = new TextField();
        taskField.setId("todo-input");

        // Botón para agregar las tareas
        var addButton = new Button("Add");
        addButton.setId("todo-button");

        // Si el texto no está vácio, se agrega un checkbox con la tarea
        addButton.addClickListener(click -> {
            if (!taskField.isEmpty()) {
                Checkbox checkbox = new Checkbox(taskField.getValue());
                todosList.add(checkbox);
                taskField.clear();
            }
        });
        // Shortcut por conveniencia
        addButton.addClickShortcut(Key.ENTER);

        // Se agregan los elementos
        add(
                new H1("To-Do List"),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                )
        );
    }
}
