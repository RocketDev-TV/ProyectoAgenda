package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.CreateContactRequestDto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.CreateContactResponseDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactsApiCtrl {
    @Autowired
    ContactsBs contactsBs;

    @PostMapping
    public ResponseEntity<CreateContactResponseDto> create(
            @RequestBody CreateContactRequestDto request,
            HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        var saved = contactsBs.save(request.toEntity(user.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateContactResponseDto.fromEntity(saved));
    }
}
