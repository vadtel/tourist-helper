package org.vadtel.touristhelper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        try {
            CityDto cityDto = cityService.getCityById(id);
            ResponseEntity<CityDto> response = new ResponseEntity<>(cityDto, HttpStatus.OK);
            log.info("{} -- Getting city by ID={}", log.getName(), id);
            return response;
        } catch (CityNotFoundException e) {
            log.error("{} -- City with ID={} not found", log.getName(), id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<CityDto>> getAllCity() {
        try {
            List<CityDto> cityDtos = cityService.getAllCity();
            ResponseEntity<List<CityDto>> response = new ResponseEntity<>(cityDtos, HttpStatus.OK);
            log.info("{} -- Getting all city", log.getName());
            return response;
        } catch (Exception e) {
            log.error("{} -- Cities not found. Cause: {}", log.getName(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        CityDto savedCityDto = cityService.saveCity(cityDto);
        log.error("{} -- Save city with ID={} and name {}", log.getName(), savedCityDto.getId(), savedCityDto.getCityName());
        ResponseEntity<CityDto> response = new ResponseEntity<>(savedCityDto, HttpStatus.OK);
        return response;
    }
}
