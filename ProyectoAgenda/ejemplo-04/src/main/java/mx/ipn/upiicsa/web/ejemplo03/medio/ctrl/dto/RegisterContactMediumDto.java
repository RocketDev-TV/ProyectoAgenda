package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterContactMediumDto {
    @NotNull(message = "El tipo de medio es obligatorio.")
    private Integer idType;

    @NotBlank(message = "El valor no puede estar vacío.")
    @Pattern(
            regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}|[0-9]{10}|@?[a-zA-Z0-9._-]{1,50})$",
            message = "Ingresa un correo, teléfono de 10 dígitos o usuario de red social (ej. @usuario)."
    )
    private String value;

    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .idType(idType)
                .value(value)
                .build();
    }
}