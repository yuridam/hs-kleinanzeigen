package de.hs.da.hskleinanzeigen;

import org.springframework.data.repository.CrudRepository;
import de.hs.da.hskleinanzeigen.model.Ad;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AdRepository extends CrudRepository<Ad, Integer> {

}