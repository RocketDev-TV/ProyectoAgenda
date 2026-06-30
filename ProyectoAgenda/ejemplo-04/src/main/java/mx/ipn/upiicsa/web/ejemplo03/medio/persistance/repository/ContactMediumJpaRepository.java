package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactMediumJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMediumJpaRepository extends JpaRepository<ContactMediumJpa, Integer> {

        List<ContactMediumJpa> findByIdContact(Integer idContact);
}