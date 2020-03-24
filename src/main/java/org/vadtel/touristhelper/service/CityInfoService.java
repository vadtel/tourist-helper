package org.vadtel.touristhelper.service;

import org.vadtel.touristhelper.dto.CityInfoDto;

import java.util.List;

public interface CityInfoService {

    void deleteCityInfoByIdAndCityId( Long cityInfoId, Long cityId);

    List<String> getAllCityInfoByCityName(String cityName);
}
