package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j; //
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.ContactDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.RegisterContactDto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.UpdateContactDto;
import java.util.Optional;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UserDto;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ContactsCtrl {

    @Autowired
    ContactsBs contactsBs;

    //lectura
    @GetMapping("/contacts")
    public String getAll(Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        var contacts = contactsBs.getContacts(user.getId());
        List<ContactDto> contactDtos = new ArrayList<>();
        for(Contact contact : contacts) {
            contactDtos.add(ContactDto.fromEntity(contact));
        }
        model.addAttribute("contacts", contactDtos);
        return "contacts/index";
    }

    //creacion
    @GetMapping("/contacts/newContact")
    public String editNew(Model model) {
        // Enviamos el DTO de registro limpio usando un constructor vacío tradicional
        model.addAttribute("registerContactDto", new RegisterContactDto());
        return "contacts/newContact";
    }

    @PostMapping("/contacts")
    public String create(
            @ModelAttribute("registerContactDto") RegisterContactDto registerContactDto,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        log.info("Creating new contact via RegisterContactDto");

        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null) {
            log.error("Usuario no encontrado en sesión");
            return "redirect:/login";
        }

        // Transformamos el DTO de registro a la entidad limpia
        Contact contactEntity = registerContactDto.toEntity();

        // Enviamos la entidad y el ID del usuario actual a la capa de negocio
        contactsBs.create(contactEntity, user.getId());

        redirectAttributes.addFlashAttribute("messageConfirmacion", "El contacto se registró exitosamente");
        return "redirect:/contacts";
    }

    //Update (es el de users todavia)
    @GetMapping("/contacts/{id}/edit")
    public String edit(@PathVariable("id") Integer idContacto,  Model model) {
        Contact resultadoContact = contactsBs.findById(idContacto);

        if(resultadoContact != null) {
            model.addAttribute("id", resultadoContact.getId());
            model.addAttribute("contactDto", UpdateContactDto.fromEntity(resultadoContact));
        } else {
            model.addAttribute("contactDto", new UpdateContactDto());
        }
        return "contacts/edit";
    }

    @PutMapping("/contacts/{id}")
    public String update(
            @PathVariable("id") Integer idContacto,
            UpdateContactDto updateContactDto,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        // Recuperamos el usuario logueado de la sesión
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null) {
            log.error("Usuario no encontrado en sesión al actualizar");
            return "redirect:/login";
        }

        Contact contact = updateContactDto.toEntity();
        contact.setId(idContacto);
        contact.setIdUser(user.getId()); // <-- 2. ASIGNAMOS EL ID DEL USUARIO (Esto resuelve el error)

        contactsBs.update(contact);

        redirectAttributes.addFlashAttribute("message", "Contacto actualizado con éxito");
        return "redirect:/contacts";
    }


    //destruccion
    @GetMapping("/contacts/{id}/destroy")
    public String destroy(@PathVariable("id") Integer idContacto, Model model) {
        // Buscamos el contacto usando el método que creamos antes
        Contact contact = contactsBs.findById(idContacto);

        if(contact != null) {
            model.addAttribute("id", contact.getId());
            // Convertimos a ContactDto para mostrar la info en el destroy.html
            model.addAttribute("contactDto", ContactDto.fromEntity(contact));
        } else {
            // En caso de error (id inexistente), mandamos un DTO vacío o redirigimos
            model.addAttribute("contactDto", new ContactDto());
        }
        return "contacts/destroy";
    }

    @DeleteMapping("/contacts/{id}")
    public String delete(@PathVariable("id") Integer idContacto, RedirectAttributes redirectAttributes) {
        // Ejecutamos la lógica de borrado
        boolean eliminado = contactsBs.delete(idContacto);

        if(eliminado) {
            redirectAttributes.addFlashAttribute("messageConfirmacion", "Contacto eliminado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("messageError", "No se pudo eliminar el contacto");
        }

        return "redirect:/contacts";
    }

}