package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.CounterfoilEntity;
import com.facturar.app.entity.InvoiceEntity;
import com.facturar.app.entity.ItemEntity;
import com.facturar.app.service.CounterfoilService;
import com.facturar.app.service.InvoiceService;
import com.lowagie.text.pdf.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CounterfoilService counterfoilService;
    @Autowired
    private SecurityUtil securityUtil;

    // Create a new Item

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InvoiceEntity invoice) {
        Long userId = securityUtil.getCurrentUserId();
        invoice.setUserId(userId);
        InvoiceEntity invoiceEntity= invoiceService.save(invoice);
        CounterfoilEntity counterfoilEntity = counterfoilService.findById(invoiceEntity.getCounterfoils_id()).get();
        Long nextNumber = counterfoilEntity.getNext_number() + 1;
        counterfoilEntity.setNext_number(nextNumber);
        counterfoilService.save(counterfoilEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceEntity);
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


    @GetMapping("/find")
    public ResponseEntity<?> findAllCodeOrName(@RequestParam(required = false) String name ) {

        Long userId = securityUtil.getCurrentUserId();
        List<InvoiceEntity> invoices = invoiceService.findAllByUserIdOrName(userId, name);


        return ResponseEntity.ok(invoices);
    }
    @RequestMapping(value = "/pdfDummy/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> pdfDummy(@PathVariable(value = "id") Long id) {
        //byte[] inFileBytes = Files.readAllBytes(Paths.get("src/main/resources/facturaWordPro.pdf").toAbsolutePath());
        try {
           // ClassPathResource pdfFile = new ClassPathResource("facturaWordPro.pdf");

            //String encoded = Base64.encodeBytes(inFileBytes);
            InputStream is = new FileInputStream(
                    new File("src/main/resources/facturaWordPro.pdf"));
                    //new File("/home/ubuntu/pdf/facturaWordPro.pdf"));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.set(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=facturaWordPro.pdf");
            return new ResponseEntity<>(
                    new InputStreamResource(is),
                    headers,
                    HttpStatus.OK);
        }catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
