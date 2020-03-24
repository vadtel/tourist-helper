package org.vadtel.touristhelper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vadtel.touristhelper.service.CityInfoService;

@RestController
@RequestMapping(value = "api/city",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CityInfoController {

    private final CityInfoService cityInfoService;

    @DeleteMapping("/{cityId}/info/{infoId}")
    public ResponseEntity<Void> deleteCityInfoByIdAndCityId(@PathVariable("infoId") Long cityInfoId,
                                                            @PathVariable("cityId") Long cityId) {
        cityInfoService.deleteCityInfoByIdAndCityId(cityInfoId, cityId);
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        log.info("{} -- Delete city info with ID={}", log.getName(), cityInfoId);
        return response;
    }
}
