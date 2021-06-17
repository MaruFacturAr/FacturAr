package com.facturar.app.controller;

import com.facturar.app.entity.VoucherType;
import com.facturar.app.service.VoucherTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/vouchertypes")
public class VoucherTypeController {

    @Autowired
    private VoucherTypeService voucherTypeService;

    @GetMapping
    public List<VoucherType> readAll(){

        List<VoucherType> voucherTypes = StreamSupport
                .stream(voucherTypeService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return voucherTypes;
    }
}
