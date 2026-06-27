package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;

@Builder
@Setter
@Getter
public class ContactMediumRequestDto {
    private Integer idType;
    private String value;

    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .idType(idType)
                .value(value)
                .build();
    }
}
