
package org.example.serviceImpl;

import com.spring.services.model.City;
import org.example.entity.CityEntity;
import org.example.repository.CityRepository;
import org.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<List<City>> getAllCities(){
        List<CityEntity> entity = cityRepository.findAll();
        List<City> modelList = new ArrayList<>();
        entity.forEach(e->{
            City model = new City();
            model.setCityId(e.getCityId());
            model.setCityName(e.getCityName());
            modelList.add(model);
        });
        return new ResponseEntity<List<City>>(modelList, HttpStatus.OK);
    }
}