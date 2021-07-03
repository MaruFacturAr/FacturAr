package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.SalesPointEntity;
import com.facturar.app.service.SalesPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/salespoints")
public class SalesPointController {

    @Autowired
    private SalesPointService salesPointService;

    @Autowired
    private SecurityUtil securityUtil;

    // Create a new Item

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SalesPointEntity salesPoint) {
        Long userId = securityUtil.getCurrentUserId();
        salesPoint.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(salesPointService.save(salesPoint));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody SalesPointEntity salesPoint) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(salesPointService.activate(salesPoint.getId(),userId));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivate(@RequestBody SalesPointEntity salesPoint) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(salesPointService.deactivate(salesPoint.getId(),userId));
    }

    // Read Items

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {

        Optional<SalesPointEntity> salesPoint = salesPointService.findById(id);

        if (!salesPoint.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salesPoint);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAllCodeOrName(@RequestParam(required = false) String code, @RequestParam(required = false) String name ) {

        Long userId = securityUtil.getCurrentUserId();
        List<SalesPointEntity> salesPointEntities = salesPointService.findAllByUserIdAndCodeOrName(userId, code, name);

        return ResponseEntity.ok(salesPointEntities);
    }
    // Obs: Maru entiendo que una factura generada no tendria que poder modificarse.
    // Por eso no hay servicio para updatear un factura.En caso constrario lo creamos

    // Read all items
    @GetMapping
    public List<SalesPointEntity> readAll() {
        Long userId = securityUtil.getCurrentUserId();
        List<SalesPointEntity> salesPointEntities = StreamSupport.stream(salesPointService.findByUserId(userId).spliterator(), false)
                .collect(Collectors.toList());

        return salesPointEntities;

    }

    //Delete item

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (!salesPointService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        salesPointService.deleteById(id);

        return new ResponseEntity<>("Se ha eliminado la factura con id " + id, HttpStatus.OK);
    }

}

