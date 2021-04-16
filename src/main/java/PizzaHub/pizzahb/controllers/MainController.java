package PizzaHub.pizzahb.controllers;

/*import PizzaHub.pizzahb.models.MenuService;*/
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository repo;


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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "signup-success";
    }
    @GetMapping("/list_users")
    public String viewUsers(Model model){
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);

        return"users";
    }



}