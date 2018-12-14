package de.hs.da.hskleinanzeigen.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Request")
public class RequestEntity extends AdvertisementEntity {

}
