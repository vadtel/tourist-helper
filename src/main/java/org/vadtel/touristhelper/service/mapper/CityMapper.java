package org.vadtel.touristhelper.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.entity.City;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", uses = CityInfoMapper.class)
public interface CityMapper {


    @Mapping(target = "cityInfoDtos", source = "cityInfos")
    CityDto toDto(City city);

    List<CityDto> toDtos(List<City> city);

    @InheritInverseConfiguration
    City toEntity(CityDto cityDto);

    default City update(City city, CityDto cityDto) {
        City sourceCity = toEntity(cityDto);
        String cityName = sourceCity.getCityName();

        if (cityName != null && !cityName.isBlank()) {
            city.setCityName(cityName);
        }

        city.setCityInfos(Stream
                .of(city.getCityInfos(), sourceCity.getCityInfos())
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .distinct()
                .filter(Objects::nonNull)
                .peek(c -> c.setCity(city))
                .collect(Collectors.toList())
        );
        return city;
    }

}
