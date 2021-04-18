package PizzaHub.pizzahb.models;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String pos_type, title, size, composition;
    private float price;

    private String mainImage;



    public String getPos_type() {
        return pos_type;
    }

    public void setPos_type(String pos_type) {
        this.pos_type = pos_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Menu() {
    }

    public Menu(String pos_type, String title, String size, String composition, float price) {
        this.pos_type = pos_type;
        this.title = title;
        this.size = size;
        this.composition = composition;
        this.price = price;
    }

    @Transient
    public String getMainImagePath(){
        if (mainImage == null || id == null) return null;
        return  "/menu-images/" + id + "/" + mainImage;
    }

}