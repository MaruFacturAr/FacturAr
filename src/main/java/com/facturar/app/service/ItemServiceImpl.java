package com.facturar.app.service;

import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public List<ItemEntity> findAllByUserIdAndCodeOrName(Long userId, String code, String name) {
        return itemDaoRepository.findAllByUserIdAndCodeOrName(userId, code, name);
    }

    @Override
    public ItemEntity activate(Long id, Long userId) {
        Optional<ItemEntity> item = itemDaoRepository.findById(id);
        item.get().setStatus_id(1);
        return  itemDaoRepository.save(item.get());
    }

    @Override
    public ItemEntity deactivate(Long id, Long userId) {
        Optional<ItemEntity> item = itemDaoRepository.findById(id);
        item.get().setStatus_id(2);
        return  itemDaoRepository.save(item.get());
    }

}

