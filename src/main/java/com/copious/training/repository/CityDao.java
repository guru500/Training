package com.copious.training.repository;

import com.copious.training.model.City;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Cacheable("city_cache")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public interface CityDao extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c")
    public List<City> findCities();
}
