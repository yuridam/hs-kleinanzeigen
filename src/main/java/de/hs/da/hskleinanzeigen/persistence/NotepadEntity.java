package de.hs.da.hskleinanzeigen.persistence;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="notepad")
public class NotepadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserEntity user;

    @OneToOne
    private AdvertisementEntity ad;

    private String note;

    private Timestamp created;

    public NotepadEntity(Integer id, UserEntity user, AdvertisementEntity ad, String note, Timestamp created) {
        this.id = id;
        this.user = user;
        this.ad = ad;
        this.note = note;
        this.created = created;
    }

    public NotepadEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AdvertisementEntity getAd() {
        return ad;
    }

    public void setAd(AdvertisementEntity ad) {
        this.ad = ad;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}

