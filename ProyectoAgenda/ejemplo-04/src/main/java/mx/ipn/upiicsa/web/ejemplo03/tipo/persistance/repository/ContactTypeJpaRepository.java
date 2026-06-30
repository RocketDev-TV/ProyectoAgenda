package mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.entity.ContactTypeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeJpaRepository extends JpaRepository<ContactTypeJpa, Integer> {

}