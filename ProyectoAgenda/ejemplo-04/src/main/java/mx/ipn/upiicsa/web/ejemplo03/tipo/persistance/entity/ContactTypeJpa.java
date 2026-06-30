package mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.tipo.bs.entity.ContactType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cag01_tipo_contacto")
public class ContactTypeJpa {

    @Id
    @Column(name = "id_tipo")
    private Integer id;

    @Column(name = "tx_nombre")
    private String name;

    @Column(name = "tx_descripcion")
    private String description;

    @Column(name = "st_activo")
    private Boolean active; // Corregido de Bool a Boolean

    // Mapeo corregido: Nombre de clase consistente
    public static ContactTypeJpa fromEntity(ContactType entity) {
        return ContactTypeJpa.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.getActive())
                .build();
    }

    public ContactType toEntity() {
        return ContactType.builder()
                .id(id)
                .name(name)
                .description(description)
                .active(active)
                .build();
    }
}