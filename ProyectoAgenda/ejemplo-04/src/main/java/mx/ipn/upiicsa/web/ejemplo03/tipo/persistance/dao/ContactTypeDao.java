package mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.tipo.bs.entity.ContactType;
import mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.entity.ContactTypeJpa;
import mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.repository.ContactTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactTypeDao {

    @Autowired
    private ContactTypeJpaRepository contactTypeJpaRepository;

    public List<ContactType> findAll() {
        List<ContactTypeJpa> jpaList = contactTypeJpaRepository.findAll();

        return jpaList.stream()
                .map(ContactTypeJpa::toEntity)
                .toList();
    }
}