package org.vadtel.touristhelper.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name", unique = true)
    private String cityName;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CityInfo> cityInfos;
}
