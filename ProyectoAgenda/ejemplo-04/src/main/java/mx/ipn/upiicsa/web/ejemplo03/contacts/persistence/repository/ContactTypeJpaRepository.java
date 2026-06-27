package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactTypeJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeJpaRepository extends JpaRepository<ContactTypeJpa, Integer> {
}
