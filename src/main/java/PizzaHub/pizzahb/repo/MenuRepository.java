package PizzaHub.pizzahb.repo;

import PizzaHub.pizzahb.models.Menu;
import org.springframework.data.repository.CrudRepository;




public interface MenuRepository extends CrudRepository<Menu, Integer> {

    Iterable<Menu> findMenuByTypeId(Integer id);


}
