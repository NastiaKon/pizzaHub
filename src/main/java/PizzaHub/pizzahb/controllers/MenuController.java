package PizzaHub.pizzahb.controllers;


import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository repo;


    @GetMapping("/menu")
    public String menuMain(Model model){
        Iterable<Menu> listPositions = repo.findAll();
        model.addAttribute("listPositions",  listPositions);
        return "menu";
    }
    @GetMapping("/menu/add")
    public String menuAdd(){
        return "menu-add";
    }

    @PostMapping("/menu/add")
    public String menuAdd(@RequestParam String pos_type, @RequestParam String title,
                             @RequestParam String size,  @RequestParam String composition, @RequestParam String price){
        Menu position = new Menu(pos_type, title, size, composition, Float.parseFloat(price)); /*comp = состав */
        repo.save(position);
        return "redirect:/menu";
    }

    @GetMapping("/menu/{id}")
    public String menuDetails(@PathVariable(value = "id") int id, Model model){
        if (!repo.existsById(id)){
            return "redirect:/menu";
        }
        Optional<Menu> position = repo.findById(id);
        ArrayList<Menu> res = new ArrayList<>();
        position.ifPresent(res::add);
        model.addAttribute("position", res);
        return "menu-details";
    }

    @GetMapping("/menu/edit/{id}")
    public String menuPosEdit(@PathVariable(value = "id") int id, Model model){
        if (!repo.existsById(id)){
            return "redirect:/menu";
        }
        Optional<Menu> position = repo.findById(id);
        ArrayList<Menu> res = new ArrayList<>();
        position.ifPresent(res::add);
        model.addAttribute("position", res);
        return "position-edit";
    }

    @PostMapping("/menu/edit/{id}")
    public String menuEdit(@PathVariable(value = "id") int id, @RequestParam String pos_type, @RequestParam String title,
                              @RequestParam String size,  @RequestParam String composition, @RequestParam String price){
        Menu position = repo.findById(id).orElseThrow();
        position.setPos_type(pos_type);
        position.setTitle(title);
        position.setSize(size);
        position.setComposition(composition);
        position.setPrice(Float.parseFloat(price));
        repo.save(position);
        return "redirect:/menu";
    }



    @GetMapping("/menu/remove/{id}")
    public String menuRemover(@PathVariable(value = "id") int id){
        return menuRemove(id);
    }

    @PostMapping("/menu/remove/{id}")
    public String menuRemove(@PathVariable(value = "id") int id){
        Menu position = repo.findById(id).orElseThrow();
        repo.delete(position);
        return "redirect:/menu";
    }



}
