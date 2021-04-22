package PizzaHub.pizzahb.services;

import PizzaHub.pizzahb.models.Menu;
import PizzaHub.pizzahb.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 *
 */
@Service("MenuService")
@Transactional
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuRepository repoMenu;

    @Override
    public Iterable<Menu> findAll() {
        return repoMenu.findAll();
    }

    @Override
    public Menu find(int id) {
        return null;
    }

    @Override
    public Optional<Menu> findByIdMenu(int id) {
        Optional<Menu> menu = repoMenu.findById(id);
        return menu;
    }


}
