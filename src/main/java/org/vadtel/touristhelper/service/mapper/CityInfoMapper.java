package org.vadtel.touristhelper.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.vadtel.touristhelper.dto.CityInfoDto;
import org.vadtel.touristhelper.entity.CityInfo;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityInfoMapper {


    CityInfoDto toDto(CityInfo cityInfo);

    List<CityInfoDto> toDtos(List<CityInfo> cityInfos);

}
