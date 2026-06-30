package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.ContactMediumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactMediumBs {

    @Autowired
    private ContactMediumDao contactMediumDao;

    //read
    @Transactional(readOnly = true)
    public List<ContactMedium> findByContact(Integer idContact) {
        return contactMediumDao.findByIdContact(idContact);
    }

    @Transactional(readOnly = true)
    public Optional<ContactMedium> findById(Integer id) {
        return contactMediumDao.findById(id);
    }

   //guardar o actualizar
    @Transactional
    public ContactMedium save(ContactMedium contactMedium) {
        return contactMediumDao.save(contactMedium);
    }

    //borrar
    @Transactional
    public void deleteById(Integer id) {
        contactMediumDao.deleteById(id);
    }
}