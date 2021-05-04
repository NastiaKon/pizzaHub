package PizzaHub.pizzahb.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title, size, composition;
    private float price;

    private String mainImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "position_types",
            joinColumns = @JoinColumn(name="menu_id"),
            inverseJoinColumns = @JoinColumn(name="type_id")
    )

    private Type type;


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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public Menu() {
    }

    public Menu(String title, String size, String composition, float price) {
        this.title = title;
        this.size = size;
        this.composition = composition;
        this.price = price;
    }

//    @Transient
//    public String getMainImagePath(){
//        if (mainImage == null || id == null) return null;
//        return  "/menu-images/" + id + "/" + mainImage;
//    }

}