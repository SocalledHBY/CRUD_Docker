package com.hby.crudbackend.service.impl;

import com.hby.crudbackend.entity.Item;
import com.hby.crudbackend.mapper.ItemMapper;
import com.hby.crudbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getAllItems() {
        return itemMapper.selectList(null);
    }

    @Override
    public Item getItemById(int itemId) {
        return itemMapper.selectById(itemId);
    }

    @Override
    public int addItem(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public int modifyItem(Item item) {
        return itemMapper.updateById(item);
    }

    @Override
    public int removeItem(int itemId) {
        return itemMapper.deleteById(itemId);
    }
}
