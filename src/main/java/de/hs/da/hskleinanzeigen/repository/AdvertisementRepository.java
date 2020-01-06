package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface AdvertisementRepository extends CrudRepository<AdvertisementEntity, Integer> {
//public interface AdvertisementRepository extends BaseAdvertisementRepository<AdvertisementEntity>, CrudRepository<AdvertisementEntity, Integer> {


    @Query(value="select * from Ad a where a.title like '%?1%' and a.location like '%?2%'", nativeQuery = true)
    List<AdvertisementEntity> findByParameters(String title, String location);


}