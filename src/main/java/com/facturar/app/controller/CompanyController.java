package com.facturar.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.CompanyEntity;
import com.facturar.app.service.CompanyService;

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

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long companyId) {

		Optional<CompanyEntity> company = companyService.findById(companyId);

		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(company);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CompanyEntity companyDetails,
			@PathVariable(value = "id") Long companyId) {

		Optional<CompanyEntity> company = companyService.findById(companyId);

		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		company.get().setBillingData(companyDetails.getBillingData());
		company.get().setIibb_code(companyDetails.getIibb_code());
		company.get().setInitial_date(companyDetails.getInitial_date());
		company.get().setUserId(companyDetails.getId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company.get()));
	}
    
    
 	@DeleteMapping("/{id}")
 	public ResponseEntity<?> delete(@PathVariable(value = "id") Long companyId) {
 		
 		if (!companyService.findById(companyId).isPresent()) {
 			return ResponseEntity.notFound().build();
 		}
 		
 		companyService.deleteById(companyId);
 		
 		return new ResponseEntity<>("Se ha eliminado la compania con id: " + companyId, HttpStatus.OK);
 	}
    
    

}
