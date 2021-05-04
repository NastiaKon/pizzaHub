package PizzaHub.pizzahb.controllers;


import PizzaHub.pizzahb.models.CustomUserDetails;
import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.Type;
import PizzaHub.pizzahb.repo.MenuRepository;
import PizzaHub.pizzahb.repo.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MenuController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MenuRepository repo;

    @Autowired
    private TypeRepository typeRepo;


    @GetMapping("/menu")
    public String menuMain(Model model, @RequestParam(required = false, defaultValue = "") Integer type_id, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Iterable<Menu> listPositions = repo.findAll();
        if (type_id != null){
            listPositions = repo.findMenuByTypeId(type_id);
        }
        model.addAttribute("listPositions",  listPositions);
        model.addAttribute("customer", customUserDetails);
        model.addAttribute("types", typeRepo.findAll());
        return "menu";
    }
    @GetMapping("/menu/add")
    public String menuAdd(Model model){
        model.addAttribute("types", typeRepo.findAll());
        return "menu-add";
    }

    @PostMapping("/menu/add")
    public String menuAdd(@RequestParam String type_food, @RequestParam String title, @RequestParam String size,  @RequestParam String composition, @RequestParam String price,
                          @RequestParam MultipartFile file) throws Exception {

        Menu position = new Menu(title, size, composition, Float.parseFloat(price));
        Type type = typeRepo.findByName(type_food);
        position.setType(type);

        if(file.getOriginalFilename().length() != 0) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            position.setMainImage(resultFileName);
        }

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
    public String menuEdit(@PathVariable(value = "id") int id, @RequestParam String title,
                              @RequestParam String size,  @RequestParam String composition, @RequestParam String price){
        Menu position = repo.findById(id).orElseThrow();
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
