package pucmm.edu.practica2.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String message = "";

    @Email
    @NotEmpty
    private String email = "";
}
