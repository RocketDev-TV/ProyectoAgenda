package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateContactDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickname;

    public Contact toEntity() {
        return Contact.builder()
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .nickname(nickname)
                .build();
    }

    public static UpdateContactDto fromEntity(Contact entity) {
        return UpdateContactDto.builder()
                .name(entity.getName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .nickname(entity.getNickname())
                .build();
    }
}

//todo es de user por el momento