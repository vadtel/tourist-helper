package org.vadtel.touristhelper.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vadtel.touristhelper.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
