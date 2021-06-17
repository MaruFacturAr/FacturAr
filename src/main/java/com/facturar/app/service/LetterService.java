package com.facturar.app.service;

import com.facturar.app.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LetterService {

    public Iterable<Letter> findAll();

    public Page<Letter> findAll(Pageable pageable);
}
