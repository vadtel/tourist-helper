package org.vadtel.touristhelper.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class CityDto {

    private Long id;

    private String cityName;

    private List<CityInfoDto> cityInfoDtos;

}
