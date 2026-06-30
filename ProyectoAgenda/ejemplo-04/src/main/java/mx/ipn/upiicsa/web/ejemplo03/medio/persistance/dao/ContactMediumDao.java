package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactMediumJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.ContactMediumJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContactMediumDao {

    @Autowired
    private ContactMediumJpaRepository contactMediumJpaRepository;

 //crear
    public ContactMedium save(ContactMedium contactMedium) {
        ContactMediumJpa jpa = ContactMediumJpa.fromEntity(contactMedium);
        ContactMediumJpa savedJpa = contactMediumJpaRepository.save(jpa);
        return savedJpa.toEntity();
    }

   //eliminar por id
    public void deleteById(Integer id) {
        contactMediumJpaRepository.deleteById(id);
    }

   //read
    public Optional<ContactMedium> findById(Integer id) {
        return contactMediumJpaRepository.findById(id)
                .map(ContactMediumJpa::toEntity);
    }

    /**
     * Recupera todos los medios asociados a un contacto en específico.
     */
    public List<ContactMedium> findByIdContact(Integer idContact) {
        List<ContactMediumJpa> jpaList = contactMediumJpaRepository.findByIdContact(idContact);
        return jpaList.stream()
                .map(ContactMediumJpa::toEntity)
                .toList();
    }
}