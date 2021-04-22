package PizzaHub.pizzahb.services;


import PizzaHub.pizzahb.models.Menu;

import java.util.Optional;


public interface MenuService {

    public Iterable<Menu> findAll();
    public Menu find (int id);
    public Optional<Menu> findByIdMenu (int id);

}