package PizzaHub.pizzahb;

import PizzaHub.pizzahb.models.Role;
import PizzaHub.pizzahb.repo.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired
    RoleRepository repo;

    @Test
    public void testCreateRoles(){
        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role customer = new Role("Customer");

        repo.saveAll(List.of(user, admin, customer));

        List<Role> listRoles = repo.findAll();
        assertThat(listRoles.size()).isEqualTo(3);

    }
}
