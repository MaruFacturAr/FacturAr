package com.facturar.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

import com.facturar.app.entity.CompanyEntity;
import com.facturar.app.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class ComapanyController {

	@Autowired
	private CompanyService companyService;

	// Create a new company

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CompanyEntity company) {
		return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
	}

	// Read company

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long companyId) {

		Optional<CompanyEntity> company = companyService.findById(companyId);

		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(company);
	}

	// Update Company
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CompanyEntity companyDetails,
			@PathVariable(value = "id") Long companyId) {

		Optional<CompanyEntity> company = companyService.findById(companyId);

		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		company.get().setUser_id(companyDetails.getUser_id());

		return ResponseEntity.ok(company);
	}

	// Delete company
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long companyId) {

		if (!companyService.findById(companyId).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		companyService.deleteById(companyId);

		return new ResponseEntity<>("Se ha eliminado la compania con id: " + companyId, HttpStatus.OK);
	}

	// Read all
	@GetMapping
	public List<CompanyEntity> readAll() {

		List<CompanyEntity> companies = StreamSupport.stream(companyService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return companies;

	}

}
