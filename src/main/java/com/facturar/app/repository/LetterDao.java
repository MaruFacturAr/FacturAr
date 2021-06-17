package com.facturar.app.repository;

import com.facturar.app.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterDao extends JpaRepository<Letter, Long> {
}
