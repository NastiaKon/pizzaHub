package PizzaHub.pizzahb.models;


import javax.persistence.*;

@Entity
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type() {
    }

    public Type(Integer id) {
        this.id = id;
    }

    public Type(String name) {
        this.name = name;
    }

    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
