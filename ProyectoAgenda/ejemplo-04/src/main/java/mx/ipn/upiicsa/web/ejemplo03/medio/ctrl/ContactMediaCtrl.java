package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.ContactMedium;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactMediumBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.RegisterContactMediumDto;
import mx.ipn.upiicsa.web.ejemplo03.tipo.bs.implementation.ContactTypeBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ContactMediaCtrl {

    @Autowired
    private ContactMediumBs contactMediumBs;

    @Autowired
    private ContactTypeBs contactTypeBs;

    /**
     * Muestra la pantalla con la lista de medios de un contacto específico.
     */
    @GetMapping("/contacts/{idContact}/media")
    public String index(@PathVariable("idContact") Integer idContact, Model model, HttpSession session) {
        if (session.getAttribute("usuarioSesion") == null) {
            return "redirect:/login";
        }
        model.addAttribute("idContact", idContact);
        model.addAttribute("mediaList", contactMediumBs.findByContact(idContact));
        return "contacts/media/index";
    }


    @GetMapping("/contacts/{idContact}/media/create")
    public String create(@PathVariable("idContact") Integer idContact, Model model, HttpSession session) {
        if (session.getAttribute("usuarioSesion") == null) {
            return "redirect:/login";
        }

        model.addAttribute("idContact", idContact);
        model.addAttribute("registerContactMediumDto", new RegisterContactMediumDto());
        model.addAttribute("types", contactTypeBs.getAllTypes());
        return "contacts/media/create";
    }

    @PostMapping("/contacts/{idContact}/media")
    public String store(
            @PathVariable("idContact") Integer idContact,
            @Valid @ModelAttribute("registerContactMediumDto")RegisterContactMediumDto dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        if (session.getAttribute("usuarioSesion") == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            // Volvemos a cargar las variables necesarias para pintar la vista de creación de nuevo
            model.addAttribute("idContact", idContact);
            model.addAttribute("types", contactTypeBs.getAllTypes());
            // Regresamos al usuario al formulario (el DTO llevará el mensaje de error arrastrando)
            return "contacts/media/create";
        }
        //flujo normal
        ContactMedium contactMedium = dto.toEntity();
        contactMedium.setIdContact(idContact);
        contactMediumBs.save(contactMedium);
        redirectAttributes.addFlashAttribute("message", "Medio de contacto agregado con éxito");
        return "redirect:/contacts/" + idContact + "/media";
    }


    @DeleteMapping("/contacts/{idContact}/media/{id}")
    public String destroy(
            @PathVariable("idContact") Integer idContact,
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        if (session.getAttribute("usuarioSesion") == null) {
            return "redirect:/login";
        }

        contactMediumBs.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Medio de contacto eliminado correctamente");
        return "redirect:/contacts/" + idContact + "/media";
    }
}