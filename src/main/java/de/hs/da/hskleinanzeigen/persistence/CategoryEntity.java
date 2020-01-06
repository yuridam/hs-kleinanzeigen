package de.hs.da.hskleinanzeigen.persistence;

import javax.persistence.*;

@Entity(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private CategoryEntity parent;

    private String name;

    public CategoryEntity(Integer id, CategoryEntity parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    public CategoryEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryEntity getParentId() {
        return parent;
    }

    public void setParentId(CategoryEntity parentId) {
        this.parent = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
