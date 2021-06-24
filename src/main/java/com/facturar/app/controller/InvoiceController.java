package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.InvoiceEntity;
import com.facturar.app.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private SecurityUtil securityUtil;

    // Create a new Item

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InvoiceEntity invoice) {
        Long userId = securityUtil.getCurrentUserId();
        invoice.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoice));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {

        Optional<InvoiceEntity> invoice = invoiceService.findById(id);

        if (!invoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<?>  readAll() {
        Long userId = securityUtil.getCurrentUserId();
        List<InvoiceEntity> invoices = invoiceService.findByUserId(userId);

        return ResponseEntity.ok(invoices);

    }
}
