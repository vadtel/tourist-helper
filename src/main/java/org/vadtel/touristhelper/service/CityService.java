package org.vadtel.touristhelper.service;

import org.vadtel.touristhelper.dto.CityDto;

import java.util.List;

public interface CityService {
    CityDto getCityById(Long id);

    List<CityDto> getAllCity();

    CityDto saveCity(CityDto cityDto);

    void deleteCityById(Long id);

    CityDto updateCity(Long id, CityDto cityDto);

    List<CityDto> getCitiesByNameContaining(String partName);
}
