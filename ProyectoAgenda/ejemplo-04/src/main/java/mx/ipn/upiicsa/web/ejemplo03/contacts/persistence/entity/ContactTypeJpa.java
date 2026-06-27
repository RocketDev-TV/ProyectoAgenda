package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cag01_tipo_contacto")
public class ContactTypeJpa {
    @Id
    @SequenceGenerator(name = "cag01_tipo_contacto_id_tipo_seq", sequenceName = "cag01_tipo_contacto_id_tipo_seq", allocationSize = 1)
    @GeneratedValue(generator = "cag01_tipo_contacto_id_tipo_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo")
    private Integer id;
    @Column(name = "tx_nombre")
    private String name;
    @Column(name = "tx_descripcion")
    private String description;
    @Column(name = "st_activo")
    private Boolean active;

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
