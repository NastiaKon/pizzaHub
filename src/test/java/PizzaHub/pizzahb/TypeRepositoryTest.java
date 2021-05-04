package PizzaHub.pizzahb;

import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.models.Role;
import PizzaHub.pizzahb.models.Type;
import PizzaHub.pizzahb.repo.MenuRepository;
import PizzaHub.pizzahb.repo.TypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class TypeRepositoryTest  {
    @Autowired
    TypeRepository typeRepo;

    @Autowired
    MenuRepository menuRepo;
    @Test
    public void testCreateTypes(){
        Type snack = new Type("Закуска");
        Type pizza= new Type("Пицца");
        Type drink = new Type("Напиток");

        typeRepo.saveAll(List.of(snack, pizza, drink));

        List<Type> listTypes = typeRepo.findAll();
        assertThat(listTypes.size()).isEqualTo(3);

    }
    @Test
    public void testAddTypeToNewPos(){
        Menu menu = new Menu();
        menu.setTitle("5 сыров");
        menu.setPrice(900);
        menu.setSize("35 см");

        Type typemenu = typeRepo.findByName("Пицца");
        menu.setType(typemenu);

        Menu savedmenu = menuRepo.save(menu);

    }


    @Test
    public void testAddRoleToOldmenu() {
        Menu menu = menuRepo.findById(3).get();

        Type typemenu = typeRepo.findByName("Пицца");
        menu.setType(typemenu);


        Menu savedmenu = menuRepo.save(menu);
    }

}
