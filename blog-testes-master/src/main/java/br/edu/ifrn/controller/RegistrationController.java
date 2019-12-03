package br.edu.ifrn.controller;

import br.edu.ifrn.model.User;
import br.edu.ifrn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {

        model.addAttribute("user", new User());
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user,
                                BindingResult bindingResult,
                                Model model) {

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Já existe um usuário cadastrado no email fornecido");
        }
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "Já existe um usuário registrado com o nome de usuário fornecido");
        }

        if (!bindingResult.hasErrors()) {
            // Registro bem sucedido, salvar usuário
            // Definir a função do usuário para USER e configurá-la como ativa
            userService.save(user);

            model.addAttribute("successMessage", "Usuário foi registrado com sucesso");
            model.addAttribute("user", new User());
        }

        return "/registration";
    }
}
