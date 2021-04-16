package PizzaHub.pizzahb.services;


import PizzaHub.pizzahb.models.Menu;


public interface MenuService {

    public Iterable<Menu> findAll();
    public Menu find(int id);

}