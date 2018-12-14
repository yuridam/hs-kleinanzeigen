package de.hs.da.hskleinanzeigen.persistence;

import de.hs.da.hskleinanzeigen.type.AdvertisementType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@DiscriminatorValue("Offer")
public class OfferEntity extends AdvertisementEntity {


}
