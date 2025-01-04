package com.antonio.skybase.repositories;

import com.antonio.skybase.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("SELECT COUNT(c) > 0 FROM Country c WHERE c.code = :code")
    boolean existsByCode(String code);
}
