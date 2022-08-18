package org.example.service;

import com.spring.services.model.City;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public ResponseEntity<List<City>> getAllCities();
}

