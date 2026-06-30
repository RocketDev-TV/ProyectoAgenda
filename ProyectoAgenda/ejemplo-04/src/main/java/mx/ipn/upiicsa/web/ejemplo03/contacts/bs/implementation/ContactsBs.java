package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.ContactsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ContactsBs {

    @Autowired
    ContactsDao contactsDao; // 2. Tipo y variable consistentes

    @Transactional(rollbackOn = Exception.class)
    public void create(Contact contact, Integer idUsuarioLogueado) {
        contact.setIdUser(idUsuarioLogueado);

        // 3. Usamos la variable exacta
        contactsDao.save(contact);
    }

    public List<Contact> getContacts(Integer idUser) {
        // 4. Usamos la variable exacta
        return contactsDao.findAll(idUser);
    }
}