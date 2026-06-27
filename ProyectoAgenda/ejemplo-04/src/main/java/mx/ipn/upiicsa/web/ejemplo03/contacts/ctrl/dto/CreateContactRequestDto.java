package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;

import java.util.List;

@Builder
@Setter
@Getter
public class CreateContactRequestDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;
    private List<ContactMediumRequestDto> mediums;

    public Contact toEntity(Integer idUser) {
        var mediumEntities = mediums != null
                ? mediums.stream().map(ContactMediumRequestDto::toEntity).toList()
                : List.of();
        return Contact.builder()
                .idUser(idUser)
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .nickName(nickName)
                .mediums(mediumEntities)
                .build();
    }
}
