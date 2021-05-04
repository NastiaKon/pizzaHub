package PizzaHub.pizzahb.services;


import PizzaHub.pizzahb.models.CustomUserDetails;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}