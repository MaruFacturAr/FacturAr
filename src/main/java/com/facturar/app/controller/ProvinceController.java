package com.facturar.app.controller;

import com.facturar.app.entity.Country;
import com.facturar.app.entity.Province;
import com.facturar.app.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public List<Province> readAll(){

        List<Province> provinces = StreamSupport
                .stream(provinceService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return provinces;
    }
}
