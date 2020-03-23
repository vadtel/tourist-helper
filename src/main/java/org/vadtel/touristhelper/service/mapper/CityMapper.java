package org.vadtel.touristhelper.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.entity.City;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    @Mapping(target = "cityInfoDtos", source = "cityInfos")
    CityDto toDto(City city);

    List<CityDto> toDtos(List<City> city);

    @InheritInverseConfiguration
    City toEntity(CityDto cityDto);

}
