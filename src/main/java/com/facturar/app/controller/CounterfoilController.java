package com.facturar.app.controller;

import com.facturar.app.config.SecurityUtil;
import com.facturar.app.entity.CounterfoilEntity;
import com.facturar.app.service.CounterfoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/counterfoils")
public class CounterfoilController {

    @Autowired
    private CounterfoilService counterfoilService;

    @Autowired
    private SecurityUtil securityUtil;

    // Create a new Item

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CounterfoilEntity counterfoil) {
        Long userId = securityUtil.getCurrentUserId();
        counterfoil.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(counterfoilService.save(counterfoil));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody CounterfoilEntity counterfoil) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(counterfoilService.activate(counterfoil.getId(),userId));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<?> deactivate(@RequestBody CounterfoilEntity counterfoil) {
        Long userId = securityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(counterfoilService.deactivate(counterfoil.getId(),userId));
    }

    // Read Items

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {

        Optional<CounterfoilEntity> item = counterfoilService.findById(userId);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findAllCodeOrName(@RequestParam(required = false) String code, @RequestParam(required = false) String name ) {

        Long userId = securityUtil.getCurrentUserId();
        List<CounterfoilEntity> item = counterfoilService.findAllByUserIdAndCodeOrName(userId, code, name);

        return ResponseEntity.ok(item);
    }
    // Obs: Maru entiendo que una factura generada no tendria que poder modificarse.
    // Por eso no hay servicio para updatear un factura.En caso constrario lo creamos

    // Read all items
    @GetMapping
    public List<CounterfoilEntity> readAll() {
        Long userId = securityUtil.getCurrentUserId();
        List<CounterfoilEntity> counterfoilEntityList = StreamSupport.stream(counterfoilService.findByUserId(userId).spliterator(), false)
                .collect(Collectors.toList());

        return counterfoilEntityList;

    }

    //Delete item

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        if (!counterfoilService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        counterfoilService.deleteById(id);

        return new ResponseEntity<>("Se ha eliminado la factura con id " + id, HttpStatus.OK);
    }
}
