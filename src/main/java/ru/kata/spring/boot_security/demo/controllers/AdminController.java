package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String allUsers(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("listOfUsers", userService.allUsers());
        model.addAttribute("listOfRoles", roleService.getAllRoles());
        return "admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.addUsers(user);
        return "redirect:/admin";
    }


    @GetMapping("/new")
    public String addNewUser(ModelMap model, @ModelAttribute("user") User user) {
        new ModelAndView("new");
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model role, @PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        role.addAttribute("listOfRoles", roleService.getAllRoles());
        return "edit";

    }


    @PatchMapping("/{id}")
    public String update(ModelMap model, @ModelAttribute("user") User user, @PathVariable("id") Long id) {
        model.addAttribute("listOfRoles", roleService.getAllRoles());
        userService.update(user, id);
        return "redirect:/admin/";
    }
}

