package com.facturar.app.service;

import com.facturar.app.entity.Letter;
import com.facturar.app.repository.LetterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService{

    @Autowired
    private LetterDao letterDao;
    @Override
    public Iterable<Letter> findAll() {
        return letterDao.findAll();
    }

    @Override
    public Page<Letter> findAll(Pageable pageable) {
        return letterDao.findAll(pageable);
    }
}
