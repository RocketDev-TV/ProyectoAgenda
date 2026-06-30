package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;

@Getter
@Setter
public class RegisterContactMediumDto {
    private Integer idType;
    private String value;


    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .idType(idType)
                .value(value)
                .build();
    }
}