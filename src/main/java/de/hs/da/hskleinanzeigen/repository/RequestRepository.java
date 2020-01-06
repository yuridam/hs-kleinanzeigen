package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.persistence.RequestEntity;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RequestRepository extends CrudRepository<RequestEntity, Integer> {



}
