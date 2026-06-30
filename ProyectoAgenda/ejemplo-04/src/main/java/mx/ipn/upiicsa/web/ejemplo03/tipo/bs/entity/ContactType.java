package mx.ipn.upiicsa.web.ejemplo03.tipo.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ContactType {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
}