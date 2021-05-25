package com.facturar.app.controller;

import com.facturar.app.entity.Country;
import com.facturar.app.entity.UserEntity;
import com.facturar.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> readAll(){

        List<Country> countries = StreamSupport
                .stream(countryService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return countries;
    }
}