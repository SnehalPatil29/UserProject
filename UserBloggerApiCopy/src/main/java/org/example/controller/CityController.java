package org.example.controller;

import com.spring.services.api.CitiesApi;
import com.spring.services.model.City;
import org.example.serviceImpl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityController implements CitiesApi {
    @Autowired
    private CityServiceImpl cityServiceImpl;


    @Override
    public ResponseEntity<List<City>> citiesGetAllCitiesGet()
    {

        return cityServiceImpl.getAllCities();
    }
}