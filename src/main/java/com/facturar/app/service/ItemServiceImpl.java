package com.facturar.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facturar.app.entity.ItemEntity;
import com.facturar.app.repository.ItemDao;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDao itemDaoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<ItemEntity> findAll() {
		return itemDaoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ItemEntity> findById(Long id) {
		return itemDaoRepository.findById(id);
	}

	@Override
	@Transactional
	public ItemEntity save(ItemEntity item) {
		return itemDaoRepository.save(item);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		itemDaoRepository.deleteById(id);
	}

}
