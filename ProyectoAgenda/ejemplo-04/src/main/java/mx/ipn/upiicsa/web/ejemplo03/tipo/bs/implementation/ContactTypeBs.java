package mx.ipn.upiicsa.web.ejemplo03.tipo.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.tipo.bs.entity.ContactType;
import mx.ipn.upiicsa.web.ejemplo03.tipo.persistence.dao.ContactTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactTypeBs {

    @Autowired
    private ContactTypeDao contactTypeDao;

    // Método principal para obtener todos los tipos de contacto (ej: para un select)
    public List<ContactType> getAllTypes() {
        return contactTypeDao.findAll();
    }
}