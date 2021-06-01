package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.CompanyEntity;
import com.facturar.app.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CompanyEntity companyEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(companyEntity));
    }
    @GetMapping
    public ResponseEntity<?> read(){
        Long id = securityUtil.getCurrentUserId();
        CompanyEntity companyEntity = companyService.findByuserId(id);
        return ResponseEntity.ok(companyEntity);
    }

}
