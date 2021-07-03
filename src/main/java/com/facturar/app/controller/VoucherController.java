package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.VoucherEntity;
import com.facturar.app.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private SecurityUtil securityUtil;

    // Create a new Item

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VoucherEntity voucherEntity) {
        Long userId = securityUtil.getCurrentUserId();
        voucherEntity.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(voucherService.save(voucherEntity));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody VoucherEntity voucherEntity) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(voucherService.activate(voucherEntity.getId(),userId));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivate(@RequestBody VoucherEntity voucherEntity) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(voucherService.deactivate(voucherEntity.getId(),userId));
    }

    // Read Items

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {

        Optional<VoucherEntity> voucherEntity = voucherService.findById(id);

        if (!voucherEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(voucherEntity);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAllCodeOrName(@RequestParam(required = false) String name ) {

        Long userId = securityUtil.getCurrentUserId();
        List<VoucherEntity> voucherEntities = voucherService.findAllByUserIdOrName(userId, name);

        return ResponseEntity.ok(voucherEntities);
    }
    // Obs: Maru entiendo que una factura generada no tendria que poder modificarse.
    // Por eso no hay servicio para updatear un factura.En caso constrario lo creamos

    // Read all items
    @GetMapping
    public List<VoucherEntity> readAll() {
        Long userId = securityUtil.getCurrentUserId();
        List<VoucherEntity> voucherEntities = StreamSupport.stream(voucherService.findByUserId(userId).spliterator(), false)
                .collect(Collectors.toList());

        return voucherEntities;

    }

    //Delete item

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (!voucherService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        voucherService.deleteById(id);

        return new ResponseEntity<>("Se ha eliminado la factura con id " + id, HttpStatus.OK);
    }

}
