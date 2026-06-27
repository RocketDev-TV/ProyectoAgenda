package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactMediumJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.ContactJpaRepository;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.ContactMediumJpaRepository;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContacsDao {
    @Autowired
    ContactJpaRepository contactJpaRepository;
    @Autowired
    ContactMediumJpaRepository contactMediumJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;

    public List<Contact> findAll(Integer idUser) {
        var userResult = userJpaRepository.findById(idUser);
        List<Contact> contacts = new ArrayList<>();
        if (userResult.isPresent()) {
            contacts = userResult.get().getContacts()
                    .stream()
                    .map(ContactJpa::toEntity)
                    .toList();
        }
        return contacts;
    }

    @Transactional
    public Contact save(Contact contact) {
        var savedContact = contactJpaRepository.save(ContactJpa.fromEntity(contact));

        List<ContactMedium> savedMediums = new ArrayList<>();
        if (contact.getMediums() != null && !contact.getMediums().isEmpty()) {
            var mediumJpaList = contact.getMediums().stream()
                    .map(m -> ContactMediumJpa.builder()
                            .idContact(savedContact.getId())
                            .idType(m.getIdType())
                            .value(m.getValue())
                            .build())
                    .toList();
            savedMediums = contactMediumJpaRepository.saveAll(mediumJpaList)
                    .stream()
                    .map(ContactMediumJpa::toEntity)
                    .toList();
        }

        return Contact.builder()
                .id(savedContact.getId())
                .idUser(savedContact.getIdUser())
                .name(savedContact.getName())
                .lastName(savedContact.getLastName())
                .secondLastName(savedContact.getSecondLastName())
                .nickName(savedContact.getNickName())
                .mediums(savedMediums)
                .build();
    }
}
