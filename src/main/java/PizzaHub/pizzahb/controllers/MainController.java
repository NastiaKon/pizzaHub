package PizzaHub.pizzahb.controllers;

/*import PizzaHub.pizzahb.models.MenuService;*/
import PizzaHub.pizzahb.models.Role;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.UserRepository;
import PizzaHub.pizzahb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService service;



    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Добро пожаловать!");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String registration(User user) {
        service.saveUserWithDefaultRole(user);
        return "signup-success";
    }
    @GetMapping("/list_users")
    public String viewUsers(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return"users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        User user = service.get(id);
        List<Role> listRoles = service.getRoles();

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/list_users";
    }


}