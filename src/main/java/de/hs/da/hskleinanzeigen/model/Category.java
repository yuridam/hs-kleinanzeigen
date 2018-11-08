package de.hs.da.hskleinanzeigen.model;



import javax.persistence.*;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer parentId;
    private String name;


    protected Category(){};

    public Category(Integer id, Integer parent_id, String name) {
        this.id = id;
        this.parentId = parent_id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parentId;
    }

    public void setParent_id(Integer parent_id) {
        this.parentId = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
