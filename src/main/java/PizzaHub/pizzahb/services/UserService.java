package PizzaHub.pizzahb.services;

import PizzaHub.pizzahb.models.Role;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.RoleRepository;
import PizzaHub.pizzahb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    public void saveUserWithDefaultRole(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

    public void save(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }

    public  User get(int id){
        return userRepo.findById(id).get();
    }

    public Optional<User> findByIdUser(int id){
        Optional<User> user = userRepo.findById(id);
        return user;

    }

    public List<Role> getRoles(){
        return roleRepo.findAll();

    }
}
