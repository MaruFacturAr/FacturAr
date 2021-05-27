package com.facturar.app.controller;

import com.facturar.app.entity.TaxpayerType;
import com.facturar.app.service.TaxpayerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/TaxpayerType")
public class TaxpayerTypeController {
    @Autowired
    private TaxpayerTypeService taxpayerTypeService;
    @GetMapping
    public List<TaxpayerType> readAll(){

        List<TaxpayerType> taxpayerTypeList = StreamSupport
                .stream(taxpayerTypeService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return taxpayerTypeList;
    }
}
