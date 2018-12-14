package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.persistence.RequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends BaseAdvertisementRepository<RequestEntity>, CrudRepository<RequestEntity, Integer> {

}
