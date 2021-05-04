package PizzaHub.pizzahb.repo;

import PizzaHub.pizzahb.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    public Type findByName(String name);
}