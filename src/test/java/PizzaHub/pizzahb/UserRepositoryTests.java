package PizzaHub.pizzahb;

import PizzaHub.pizzahb.models.Role;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.RoleRepository;
import PizzaHub.pizzahb.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)

public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("admin@mail.ru");
        user.setPassword("12345");
        user.setFirstName("Nastya");
        user.setLastName("Klimovich");

        User savedUser = userRepo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email = "admin@mail.ru";

        User user = userRepo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRoleToNewUser(){
        User user = new User();
        user.setEmail("whoami@mail.ru");
        user.setPassword("12345");
        user.setFirstName("Who");
        user.setLastName("Where");

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }


    @Test
    public void testAddRoleToOldUser(){
        User user = userRepo.findById(2).get();

        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin = new Role(2);
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);


    }
}
