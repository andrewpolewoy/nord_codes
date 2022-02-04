package com.nord.shorter.controller;

import com.nord.shorter.model.User;
import com.nord.shorter.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RegistrationController {

    private IUserService userService;

    @Autowired
    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm")
                          @Valid User userForm,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String getSignInPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogInPage() {
        return "login";
    }

    @GetMapping("/user")
    public String userList(Principal principal) {
        String url;
        if (principal == null) {
            url = "/";
        } else {

//            User user = userService.findByUsername(request.getUserPrincipal().getName());
            User user = userService.findByUsername(principal.getName());
            url = "redirect:/user/" + user.getId();
        }

        return "user";
    }
}
