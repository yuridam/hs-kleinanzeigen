package de.hs.da.hskleinanzeigen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Enum type;
    private String title;
    private Integer category_id;
    private String description;
    private float price;
    private String location;
    private String timestamp;

    public Ad(Integer id, Enum type, Integer category_id, String title, String description, float price, String location, String timestamp) {
        this.id = id;
        this.type = type;
        this.category_id = category_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}