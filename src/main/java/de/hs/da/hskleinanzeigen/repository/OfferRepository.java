package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.persistence.OfferEntity;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends BaseAdvertisementRepository<OfferEntity>, CrudRepository<OfferEntity, Integer> {

}
