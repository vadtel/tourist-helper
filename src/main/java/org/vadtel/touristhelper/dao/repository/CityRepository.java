package org.vadtel.touristhelper.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadtel.touristhelper.entity.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCityNameIgnoreCaseContaining(String name);
    Optional<City> findByCityName(String name);
}
