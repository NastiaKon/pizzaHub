package PizzaHub.pizzahb;


import PizzaHub.pizzahb.models.CartItem;
import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ShoppingCartTests {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem(){
        Menu position = entityManager.find(Menu.class, 21);
        User user = entityManager.find(User.class, 12);

        CartItem newItem = new CartItem();
        newItem.setUser(user);
        newItem.setMenu(position);
        newItem.setQuantity(1);

        CartItem savedCartItem = cartRepo.save(newItem);

        assertTrue(savedCartItem.getId()>0);
    }
    @Test
    public void testGetCartItemsByCustomer(){
       User user = new User();
       user.setId(12);

       List<CartItem> cartItems = cartRepo.findByUser(user);

       assertEquals(2, cartItems.size());
    }
    @Test
    public void testFindByEmail(){
        User user = new User();
        user.setEmail("stasy@yandex.ru");
        List<CartItem> cartItems = cartRepo.findByUser(user);

        assertEquals(2, cartItems.size());
    }
}
