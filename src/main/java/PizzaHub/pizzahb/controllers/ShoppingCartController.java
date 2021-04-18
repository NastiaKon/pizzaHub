package PizzaHub.pizzahb.controllers;



import PizzaHub.pizzahb.models.CartItem;
import PizzaHub.pizzahb.models.CustomUserDetails;
import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.User;
import PizzaHub.pizzahb.repo.MenuRepository;
import PizzaHub.pizzahb.services.CustomUserDetailsService;
import PizzaHub.pizzahb.services.ShoppingCartServices;
import PizzaHub.pizzahb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ShoppingCartController {

    @Autowired
    private MenuRepository menuService;

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
}

//    @GetMapping("/buy/menu/{id}")
//    public String cartBuy(@PathVariable(value = "id") int id, Model model, HttpSession session){
//        if(session.getAttribute("cart") == null) {
//            Optional<Menu> cart = menuService.findById(id);
//            ArrayList<Menu> res = new ArrayList<>();
//            cart.ifPresent(res::add);
//            session.setAttribute("cart", res);}
//        else {
//        }
//        return "redirect:/cart-details";
//    }
//}
