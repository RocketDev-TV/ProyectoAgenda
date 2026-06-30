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
    // Esta expresión regular permite tanto correos válidos como números telefónicos de 10 dígitos
    @Pattern(
            regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}|[0-9]{10})$",
            message = "El formato introducido no corresponde a un correo válido o un teléfono de 10 dígitos."
    )
    private String value;

    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .idType(idType)
                .value(value)
                .build();
    }
}