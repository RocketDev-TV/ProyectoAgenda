package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ContactMedium {
    private Integer id;
    private Integer idContact;
    private Integer idType;
    private String value;
}