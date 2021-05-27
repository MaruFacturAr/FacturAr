package com.facturar.app.controller;

import com.facturar.app.entity.DocumentType;
import com.facturar.app.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/DocumentType")
public class DocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;
    @GetMapping
    public List<DocumentType> readAll(){

        List<DocumentType> documentTypeList = StreamSupport
                .stream(documentTypeService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return documentTypeList;
    }
}
