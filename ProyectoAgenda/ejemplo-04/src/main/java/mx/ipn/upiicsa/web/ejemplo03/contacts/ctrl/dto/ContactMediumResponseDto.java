package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;

@Builder
@Setter
@Getter
public class ContactMediumResponseDto {
    private Integer id;
    private Integer idType;
    private String value;

    public static ContactMediumResponseDto fromEntity(ContactMedium entity) {
        return ContactMediumResponseDto.builder()
                .id(entity.getId())
                .idType(entity.getIdType())
                .value(entity.getValue())
                .build();
    }
}
