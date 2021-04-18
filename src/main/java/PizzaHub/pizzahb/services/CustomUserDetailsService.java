package PizzaHub.pizzahb.services;


import PizzaHub.pizzahb.models.CustomUserDetails;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Такого пользователя нет");
        }

        return new CustomUserDetails(user);
    }

//    public User getCurrentlyLoggedInUser(Authentication authentication) {
//        if (authentication == null) {
//            System.out.println("auth is null");
//            return null;
//        }
//
//
//        User user = null;
//        Object principal = authentication.getPrincipal();
//
//        if (principal instanceof CustomUserDetails) {
//            System.out.println(((CustomUserDetails)principal).getUsername());
//            String email = ((CustomUserDetails)principal).getUsername();
//            user = repo.findByEmail(email);
//        }
//        return user;
//    }
}