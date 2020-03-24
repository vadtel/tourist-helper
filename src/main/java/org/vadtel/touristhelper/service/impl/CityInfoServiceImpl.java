package org.vadtel.touristhelper.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.touristhelper.dao.repository.CityInfoRepository;
import org.vadtel.touristhelper.dao.repository.CityRepository;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.dto.CityInfoDto;
import org.vadtel.touristhelper.entity.City;
import org.vadtel.touristhelper.service.CityInfoService;
import org.vadtel.touristhelper.service.mapper.CityMapper;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional
public class CityInfoServiceImpl implements CityInfoService {

    private final CityInfoRepository cityInfoRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public void deleteCityInfoByIdAndCityId(Long cityInfoId, Long cityId) {
        cityInfoRepository.deleteByIdAndCity_Id(cityInfoId, cityId);
    }

    @Override
    public List<String> getAllCityInfoByCityName(String cityName) {
        City city = cityRepository.findByCityName(cityName).orElse(null);

        if (city != null) {
            CityDto cityDto = cityMapper.toDto(city);
            List<CityInfoDto> cityInfoDtos = cityDto.getCityInfos();
            List<String> cityInfos = Collections.emptyList();
            if (cityInfoDtos != null) {
                cityInfos = cityInfoDtos.stream()
                        .map(CityInfoDto::getCityInfo)
                        .collect(Collectors.toList());
            }
            return cityInfos;
        } else {
            return null;
        }
    }
}
