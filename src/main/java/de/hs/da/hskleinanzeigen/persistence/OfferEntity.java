package de.hs.da.hskleinanzeigen.persistence;

import de.hs.da.hskleinanzeigen.type.AdvertisementType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("Offer")
public class OfferEntity extends AdvertisementEntity {

    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
