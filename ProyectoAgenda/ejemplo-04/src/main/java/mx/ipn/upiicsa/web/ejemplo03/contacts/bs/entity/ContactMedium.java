package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ContactMedium {
    private Integer id;
    private Integer idContact;
    private Integer idType;
    private String value;
}
