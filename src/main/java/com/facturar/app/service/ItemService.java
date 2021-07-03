package com.facturar.app.service;

import java.util.List;
import java.util.Optional;

import com.facturar.app.entity.ItemEntity;

public interface ItemService {

    public Iterable<ItemEntity> findAll();

    public Optional<ItemEntity> findById(Long id);

    public ItemEntity save(ItemEntity item);

    public void deleteById(Long id);

    public List<ItemEntity> findAllByUserIdAndCodeOrName(Long userId, String code,
                                                 String name);
    public ItemEntity activate(Long id, Long userId);

    public ItemEntity deactivate(Long id, Long userId);

    public Iterable<ItemEntity> findByUserId(Long userId);
}
