package com.hby.crudbackend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hby.crudbackend.entity.Item;
import com.hby.crudbackend.mapper.ItemMapper;
import com.hby.crudbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Item> getAllItems(int page) {
        int pageSize = 10;

        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        List<Object> items = listOps.range("items" + page, 0, pageSize);
        if (items != null && !items.isEmpty()) {
            return items.stream()
                    .map(obj -> (Item) obj)
                    .toList();
        }

        PageHelper.startPage(page, pageSize);
        List<Item> newItems = itemMapper.selectList(null);
        redisTemplate.opsForList().rightPushAll("items" + page, new ArrayList<>(newItems));

        String total = stringRedisTemplate.opsForValue().get("total");
        if (total == null) {
            PageInfo<Item> pageInfo = new PageInfo<>(newItems);
            stringRedisTemplate.opsForValue().set("total", String.valueOf(pageInfo.getTotal()));
        }

        return newItems;
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
