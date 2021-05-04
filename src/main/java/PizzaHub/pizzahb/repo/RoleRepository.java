package PizzaHub.pizzahb.repo;

import PizzaHub.pizzahb.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);

}
