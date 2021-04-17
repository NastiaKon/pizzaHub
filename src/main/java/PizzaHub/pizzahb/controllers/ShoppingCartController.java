package PizzaHub.pizzahb.controllers;



import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ShoppingCartController {

    @Autowired
    private MenuRepository MenuService;

    @RequestMapping(method = RequestMethod.GET)
    public String cartInfo(){
        return "cart-details";
    }


    @GetMapping("/buy/menu/{id}")
    public String cartBuy(@PathVariable(value = "id") int id, Model model, HttpSession session){
        if(session.getAttribute("cart") == null) {
            Optional<Menu> cart = MenuService.findById(id);
            ArrayList<Menu> res = new ArrayList<>();
            cart.ifPresent(res::add);
            session.setAttribute("cart", res);}
        else {
        }
        return "redirect:/cart-details";
    }
}
