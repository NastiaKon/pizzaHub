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
        return cartRepo.findByUser(user);
    }

    public List<CartItem> listCartMenu (Menu menu){
        return cartRepo.findPosById(menu);
    }

//
//    public Optional<CartItem> findByIdCartItem(long id){
//        Optional<CartItem> cartItem = cartRepo.findById((int) id);
//        return cartItem;
//    }


//    public int addToCart(int pos_id, int quantity, User user){
//        int addedQuantity = quantity;
//
//        Menu position = menuRepo.findById(pos_id).get();
//
//        CartItem cartItem = cartRepo.findByUserAndPosition(user, position);
//
//        if (cartItem != null) {
//            addedQuantity = cartItem.getQuantity() + quantity;
//            cartItem.setQuantity(addedQuantity);
//        } else {
//            cartItem = new CartItem();
//            cartItem.setQuantity(quantity);
//            cartItem.setUser(user);
//            cartItem.setMenu(position);
//        }
//        cartRepo.save(cartItem);
//
//        return addedQuantity;
//
//    }






}