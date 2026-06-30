package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.entity.ContactTypeJpa;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tag03_medio_contacto")
public class ContactMediumJpa {

    @Id
    @SequenceGenerator(name = "tag03_medio_contacto_id_medio_contacto_seq", sequenceName = "tag03_medio_contacto_id_medio_contacto_seq", allocationSize = 1)
    @GeneratedValue(generator = "tag03_medio_contacto_id_medio_contacto_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medio_contacto")
    private Integer id;

    @Column(name = "fk_id_contacto")
    private Integer idContact;
    @Column(name = "fk_id_tipo")
    private Integer idType;
    @Column(name = "tx_valor")
    private String value;
    @ManyToOne
    @JoinColumn(name = "fk_id_contacto", referencedColumnName = "id_contacto", insertable = false, updatable = false)
    private ContactJpa contact;
    @ManyToOne
    @JoinColumn(name = "fk_id_tipo", referencedColumnName = "id_tipo", insertable = false, updatable = false)
    private ContactTypeJpa type;

    public static ContactMediumJpa fromEntity(ContactMedium entity) {
        return ContactMediumJpa.builder()
                .id(entity.getId())
                .idContact(entity.getIdContact())
                .idType(entity.getIdType())
                .value(entity.getValue())
                .build();
    }

    public ContactMedium toEntity() {
        return ContactMedium.builder()
                .id(id)
                .idContact(idContact)
                .idType(idType)
                .value(value)
                .build();
    }
}