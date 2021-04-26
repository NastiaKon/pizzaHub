package PizzaHub.pizzahb.controllers;


import PizzaHub.pizzahb.models.CartItem;
import PizzaHub.pizzahb.models.CustomUserDetails;
import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.CartItemRepository;
import PizzaHub.pizzahb.repo.MenuRepository;
import PizzaHub.pizzahb.services.CustomUserDetailsService;
import PizzaHub.pizzahb.services.ShoppingCartServices;
import PizzaHub.pizzahb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class ShoppingCartController {

    @Autowired
    private MenuRepository menuService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartServices cartServices;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        User user = userService.findByEmail(customUserDetails.getUsername());
        List<CartItem> cartItems = cartServices.listCartItems(user);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");

        return "shopping_cart";
    }

    @PostMapping("/addItem")
    public String addItemToShoppingCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @RequestParam String user_id, @RequestParam int menu_id, @RequestParam int quantity) {
        User user = userService.findByEmail(user_id);
        Menu menu = menuService.findById(menu_id).orElseThrow();
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setMenu(menu);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return "redirect:/menu";
    }


//    @GetMapping("/cartItem/{id}/delete")
//    public String deleteFromCart(@PathVariable(value = "id") int id){
//        return deleteFromCart(id);
//    }
//
//    @PostMapping("/cartItem/{id}/delete")
//    public String deleteFromCart(@PathVariable(value = "id") int id) {
//        Menu position = menuService.findById(id).orElseThrow();
//        CartItem cartItem = cartServices.findByIdCartItem(id).orElseThrow();
//        cartServices.(position);
//
//    }
}



