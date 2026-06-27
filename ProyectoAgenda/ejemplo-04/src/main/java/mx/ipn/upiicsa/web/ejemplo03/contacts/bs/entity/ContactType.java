package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ContactType {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
}
