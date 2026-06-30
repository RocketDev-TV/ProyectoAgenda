package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterContactDto {
    // Solo los datos que el usuario ingresa en la pantalla de registro
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;

    // Método para transformar directo a Entidad de Negocio
    public Contact toEntity() {
        return Contact.builder()
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .nickName(nickName)
                .build();
    }
}