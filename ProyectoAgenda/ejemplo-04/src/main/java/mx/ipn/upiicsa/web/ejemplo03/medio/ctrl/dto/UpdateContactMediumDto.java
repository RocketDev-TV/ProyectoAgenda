package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;

@Getter
@Setter
public class UpdateContactMediumDto {
    private Integer id;
    private Integer idType;

    @NotBlank(message = "El valor no puede estar vacío.")
    @Pattern(
            regexp = "^([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}|[0-9]{10}|@?[a-zA-Z0-9._-]{1,50})$",
            message = "Ingresa un correo, teléfono de 10 dígitos o usuario de red social (ej. @usuario)."
    )
    private String value;

    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .id(id)
                .idType(idType)
                .value(value)
                .build();
    }

    public static UpdateContactMediumDto fromEntity(ContactMedium entity) {
        UpdateContactMediumDto dto = new UpdateContactMediumDto();
        dto.setId(entity.getId());
        dto.setIdType(entity.getIdType());
        dto.setValue(entity.getValue());
        return dto;
    }
}