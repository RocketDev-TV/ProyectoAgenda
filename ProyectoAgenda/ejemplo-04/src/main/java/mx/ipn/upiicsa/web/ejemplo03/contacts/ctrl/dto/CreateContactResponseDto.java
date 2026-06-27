package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;

import java.util.List;

@Builder
@Setter
@Getter
public class CreateContactResponseDto {
    private Integer id;
    private Integer idUser;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;
    private List<ContactMediumResponseDto> mediums;

    public static CreateContactResponseDto fromEntity(Contact entity) {
        var mediumDtos = entity.getMediums() != null
                ? entity.getMediums().stream().map(ContactMediumResponseDto::fromEntity).toList()
                : List.of();
        return CreateContactResponseDto.builder()
                .id(entity.getId())
                .idUser(entity.getIdUser())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .nickName(entity.getNickName())
                .mediums(mediumDtos)
                .build();
    }
}
