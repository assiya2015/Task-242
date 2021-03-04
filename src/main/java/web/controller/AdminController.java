package web.controller;

import org.springframework.web.bind.annotation.*;
import web.entity.Role;
import web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import web.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model theModel) {
        theModel.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/index")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @RequestMapping("/update/{id}")
    //@PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(userService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete/{id}")
    //@DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/admin";
    }

    // JSP realization


//    @GetMapping("/list")
//    public String listCustomers(Model theModel) {
//        List<User> users = userService.getUsers();
//        theModel.addAttribute("users", users);
//        return "list-users";
//    }
//
//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//        User user = new User();
//        theModel.addAttribute("user", user);
//        return "user-form";
//    }
//
//    @PostMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/list";
//    }
//
//    @GetMapping("/showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("userId") long theId,
//                                    Model theModel) {
//        User user = userService.getUser(theId);
//        theModel.addAttribute("user", user);
//        return "update-form";
//    }
//
//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam("userId") long theId) {
//        userService.deleteUser(userService.getUser(theId));
//        return "redirect:/list";
//    }
//
//    @PostMapping("/updateUser")
//    public String updateUser(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute(userService.updateUser(user));
//        return "redirect:/list";
//    }
}

