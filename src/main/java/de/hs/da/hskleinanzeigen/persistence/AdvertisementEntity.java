package de.hs.da.hskleinanzeigen.persistence;

import de.hs.da.hskleinanzeigen.type.AdvertisementType;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name = "ad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class AdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    @Column(name = "type", columnDefinition = "ENUM('Offer', 'Request')")
    @Enumerated(value = EnumType.STRING)
    private AdvertisementType type;
    */

    @ManyToOne
    private CategoryEntity category;

    private String title;

    private String description;

    private float price;

    private String location;

    private Timestamp created;

    public AdvertisementEntity(Integer id, CategoryEntity category, String title, String description, float price, String location, Timestamp created) {
        this.id = id;
        //this.type = type;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
        this.created = created;
    }

    public AdvertisementEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*
    public AdvertisementType getType() {
        return type;
    }

    public void setType(AdvertisementType type) {
        this.type = type;
    }
    */
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
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
