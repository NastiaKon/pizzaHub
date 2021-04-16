package PizzaHub.pizzahb.repo;

import PizzaHub.pizzahb.models.CartItem;
import PizzaHub.pizzahb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    public List<CartItem> findByUser(User user);


}