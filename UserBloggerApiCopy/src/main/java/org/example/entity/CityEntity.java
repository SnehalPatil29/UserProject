package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name="cities",schema = "public")
public class CityEntity {
    @Id
    @Column(name="city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;
    @Column(name="city_name")
    private String cityName;

    public CityEntity() {
    }

    public CityEntity(Integer cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}