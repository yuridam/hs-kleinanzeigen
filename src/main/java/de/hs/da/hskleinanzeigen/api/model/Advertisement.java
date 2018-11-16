package de.hs.da.hskleinanzeigen.api.model;

import de.hs.da.hskleinanzeigen.type.AdvertisementType;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Advertisement {

    @NotNull
    private Integer id;

    @NotNull
    private AdvertisementType type;

    @NotNull
    private Category category;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private float price;

    private String location;

    @NotNull
    private Timestamp created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdvertisementType getType() {
        return type;
    }

    public void setType(AdvertisementType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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