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
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto getCityById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(String.format("Город с ID=%s не найден", id)));
        CityDto cityDto = cityMapper.toDto(city);
        return cityDto;
    }

    @Override
    public List<CityDto> getAllCity() {
        List<City> allCities = cityRepository.findAll();
        if (allCities.isEmpty()) {
            throw new CityNotFoundException("Городов в базе нет");
        }
        List<CityDto> cityDtos = cityMapper.toDtos(allCities);
        return cityDtos;
    }

    @Override
    public CityDto saveCity(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        Optional.ofNullable(city.getCityInfos())
                .ifPresent(c -> c.forEach(x -> x.setCity(city)));
        City savedCity = cityRepository.save(city);
        return cityMapper.toDto(savedCity);
    }

    @Override
    public CityDto updateCity(Long id, CityDto cityDto) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(String.format("Город с ID=%s не найден", id)));
        City updatedCity = cityMapper.update(city, cityDto);
        cityRepository.save(updatedCity);
        return cityMapper.toDto(updatedCity);
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }


    @Override
    public List<CityDto> getCitiesByNameContaining(String partName){
        List<City> cities = cityRepository.findByCityNameIgnoreCaseContaining(partName);
        List<CityDto> cityDtos = cityMapper.toDtos(cities);
        return cityDtos;
    }
}
