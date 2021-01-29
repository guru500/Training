package com.copious.training.repository;

import com.copious.training.model.CityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Repository
public interface CityViewDao /*extends JpaRepository<CityView, Integer>*/ {

    @Query(value = "SELECT Name FROM city_view WHERE id=(:id)", nativeQuery = true)
    public Optional<List<CityView>> getCityViewList(@Param("id") int id);

    @Procedure(name = "city_procedure")
    public HashMap<String, Object> getDataFromStoredProcedure(@Param("id") int id);
}
