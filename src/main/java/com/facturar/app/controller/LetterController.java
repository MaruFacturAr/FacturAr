package com.facturar.app.controller;

import com.facturar.app.entity.Letter;
import com.facturar.app.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/letters")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @GetMapping
    public List<Letter> readAll(){

        List<Letter> letters = StreamSupport
                .stream(letterService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return letters;
    }
}
