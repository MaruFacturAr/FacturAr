package com.facturar.app.service;

import java.util.Optional;

import com.facturar.app.entity.ItemEntity;

public interface ItemService {
	
	public Iterable<ItemEntity> findAll();
	
	public Optional<ItemEntity> findById(Long id);
	
	public ItemEntity save(ItemEntity item);
	
	public void deleteById(Long id);
	
}
