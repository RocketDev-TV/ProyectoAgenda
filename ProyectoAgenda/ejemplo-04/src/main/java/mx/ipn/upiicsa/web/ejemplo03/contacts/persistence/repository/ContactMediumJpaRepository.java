package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactMediumJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMediumJpaRepository extends JpaRepository<ContactMediumJpa, Integer> {
}
