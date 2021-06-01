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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facturar.app.entity.ItemEntity;
import com.facturar.app.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create a new Item

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ItemEntity item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(item));
    }

    // Read Items

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {

        Optional<ItemEntity> item = itemService.findById(userId);

        if (!item.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    // Obs: Maru entiendo que una factura generada no tendria que poder modificarse.
    // Por eso no hay servicio para updatear un factura.En caso constrario lo creamos

    // Read all items
    @GetMapping
    public List<ItemEntity> readAll() {

        List<ItemEntity> items = StreamSupport.stream(itemService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return items;

    }

    //Delete item

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long idItem) {

        if (!itemService.findById(idItem).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        itemService.deleteById(idItem);

        return new ResponseEntity<>("Se ha eliminado la factura con id " + idItem, HttpStatus.OK);
    }

}
