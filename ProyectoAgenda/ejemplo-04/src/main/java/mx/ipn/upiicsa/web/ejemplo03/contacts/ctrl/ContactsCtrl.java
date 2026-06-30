package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j; // 1. IMPORTANTE: Agregado para usar log
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // 2. IMPORTANTE: Agregado para redirecciones flash
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.RegisterContactDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ContactsCtrl {

    @Autowired
    ContactsBs contactsBs; // Este es el nombre real con 's'

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
}