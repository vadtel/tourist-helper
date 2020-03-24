package org.vadtel.touristhelper.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vadtel.touristhelper.entity.CityInfo;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {

    void deleteByIdAndCity_Id(Long cityInfoId, Long cityId);
}
