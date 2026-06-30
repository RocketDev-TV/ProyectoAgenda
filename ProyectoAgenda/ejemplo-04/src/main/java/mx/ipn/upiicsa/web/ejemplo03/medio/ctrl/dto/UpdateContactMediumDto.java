package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;

@Getter
@Setter
public class UpdateContactMediumDto {
    private Integer id;
    private Integer idType;
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