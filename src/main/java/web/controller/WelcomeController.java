package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.RoleDao;
import web.entity.Role;
import web.entity.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final UserService userService;


    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcome() {
        return "welcome/welcome";
    }

}
