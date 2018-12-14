package de.hs.da.hskleinanzeigen.repository;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseAdvertisementRepository <EntityType extends AdvertisementEntity> extends CrudRepository<EntityType, Integer> {

    @Query("select e from #{#entityName} e") // #{#entityName} will be magically replaced by type arguments in children
    List<EntityType> findThemAll();
}
