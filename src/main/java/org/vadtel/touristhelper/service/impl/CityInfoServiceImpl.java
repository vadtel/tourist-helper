package org.vadtel.touristhelper.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vadtel.touristhelper.dao.repository.CityInfoRepository;
import org.vadtel.touristhelper.service.CityInfoService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional
public class CityInfoServiceImpl implements CityInfoService {

    private final CityInfoRepository cityInfoRepository;

    @Override
    public void deleteCityInfoByIdAndCityId(Long cityInfoId, Long cityId){
        cityInfoRepository.deleteByIdAndCity_Id(cityInfoId, cityId);
    }
}
