package org.vadtel.touristhelper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.exception.CityNotFoundException;
import org.vadtel.touristhelper.service.CityService;

import java.util.List;

@RestController
@RequestMapping(value = "api/city",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CityController {

    private final CityService cityService;

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable("id") Long id) {
        log.debug("Get request getCityById()" + log.getName());
        try {
            CityDto cityDto = cityService.getCityById(id);
            ResponseEntity<CityDto> response = new ResponseEntity<>(cityDto, HttpStatus.OK);
            return response;
        } catch (CityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<CityDto>> getAllCity() {

        log.info("Get request getAllCity()" + log.getName());
        try {
            List<CityDto> cityDtos = cityService.getAllCity();
            ResponseEntity<List<CityDto>> response = new ResponseEntity<>(cityDtos, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
