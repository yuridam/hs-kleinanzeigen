package de.hs.da.hskleinanzeigen.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="type", columnDefinition = "ENUM('Offer', 'Request')")
    @Enumerated(value = EnumType.STRING)
    private AdType type;
    private String title;
    private String description;
    private float price;
    private String location;
    @ManyToOne
    private Category category;
    private Timestamp created;

    public Ad(Integer id, AdType type, String title, String description, float price, String location,Category category, Timestamp created) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.category = category;
        this.created = created;
    }

    protected Ad() {

    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdType getType() {
        return type;
    }

    public void setType(AdType type) {
        this.type = type;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}