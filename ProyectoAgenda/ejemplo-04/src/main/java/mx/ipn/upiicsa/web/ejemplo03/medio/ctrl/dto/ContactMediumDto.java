package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContactMediumDto {
    private Integer id;
    private Integer idContact;
    private Integer idType;
    private String typeName; // Nombre del tipo (ej: "Instagram") para mostrarlo en la tabla
    private String value;     // El valor (ej: "@usuario")
}