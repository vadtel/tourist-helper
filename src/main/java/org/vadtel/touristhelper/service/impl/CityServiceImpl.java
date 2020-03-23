package org.vadtel.touristhelper.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.touristhelper.dao.repository.CityRepository;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.entity.City;
import org.vadtel.touristhelper.exception.CityNotFoundException;
import org.vadtel.touristhelper.service.CityService;
import org.vadtel.touristhelper.service.mapper.CityMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        CityDto cityDto = cityMapper.toDto(city);
        return cityDto;
    }

    @Override
    public List<CityDto> getAllCity(){
        List<City> allCities = cityRepository.findAll();
        List<CityDto> cityDtos = cityMapper.toDtos(allCities);

        return cityDtos;
    }
}
