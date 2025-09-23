package com.hby.crudbackend.service;

import com.hby.crudbackend.entity.Item;

import java.util.List;

public interface ItemService {

    public List<Item> getAllItems();

    public Item getItemById(int itemId);

    public int addItem(Item item);

    public int modifyItem(Item item);

    public int removeItem(int itemId);
}
