package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.UserBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.RegisterUserDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UpdateUserDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UserDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
public class UsersCtrl {
    @Autowired
    UserBs userBs;
    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public String index(Model model) {
        var users = userBs.findAll();
        var usersDto = new ArrayList<UserDto>();
        for(var user : users) {
            usersDto.add(UserDto.fromEntity(user));
            log.info("{} {}", user.getId(), user.getUsername());
        }
        model.addAttribute("personas", usersDto);
        return "users/index";
    }


    //denegar la edicion de usuarios ajenos
    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Integer idUsuario, Model model, HttpSession session) {
        // 1. Recuperar el usuario usando la clase original de tu sesión
        User usuarioSesion = (User) session.getAttribute("usuarioSesion");

        // 2. Si no hay sesión iniciada, rebota al login
        if (usuarioSesion == null) {
            return "redirect:/login";
        }

       if (!usuarioSesion.getId().equals(idUsuario)) {
            return "redirect:/contacts?error=NoAutorizado";
        }

        Optional<User> resultadoUser = userBs.findById(idUsuario);
        if (resultadoUser.isPresent()) {
            model.addAttribute("id", resultadoUser.get().getId());
            model.addAttribute("userDto", UpdateUserDto.fromEntity(resultadoUser.get()));
        } else {
            return "redirect:/contacts";
        }

        return "users/edit";
    }



    @PutMapping("/users/{id}")
    public String update(@PathVariable("id") Integer idUsuario, UpdateUserDto updateUserDto, Model model, RedirectAttributes redirectAttributes) {
        User user = updateUserDto.toEntity();
        user.setId(idUsuario);
        var resultdo = userBs.update(user);
        redirectAttributes.addFlashAttribute("message", "Usuario actualizado con exito");
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/destroy")
    public String destroy(@PathVariable("id") Integer idUsuario,  Model model) {
        Optional<User> resultadoUser= userBs.findById(idUsuario);
        if(resultadoUser.isPresent()) {
            model.addAttribute("id", resultadoUser.get().getId());
            model.addAttribute("userDto", UpdateUserDto.fromEntity(resultadoUser.get()));
        } else {
            model.addAttribute("userDto", new UpdateUserDto());
        }
        return "users/destroy";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Integer idUsuario, Model model, RedirectAttributes redirectAttributes) {
        var resultdo = userBs.delete(idUsuario);
        redirectAttributes.addFlashAttribute("message", "Usuario actualizado con exito");
        return "redirect:/users";
    }
}
