package PizzaHub.pizzahb.services;

import PizzaHub.pizzahb.models.CartItem;
import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.CartItemRepository;
import PizzaHub.pizzahb.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServices {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private MenuRepository menuRepo;

    public List<CartItem> listCartItems (User user){
        return cartRepo.findByUserAndStatusFalse(user);
    }

    public List<CartItem> listCartMenu (Menu menu){
        return cartRepo.findPosById(menu);
    }

}