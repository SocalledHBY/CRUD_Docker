package com.hby.crudbackend.controller;

import com.hby.crudbackend.entity.R;
import com.hby.crudbackend.entity.Item;
import com.hby.crudbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/items")
    public R<Map<String,Object>> getAllItems(@RequestParam(defaultValue = "1") int page) {
        List<Item> itemList = itemService.getAllItems(page);

        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("total", stringRedisTemplate.opsForValue().get("total"));
        pageMap.put("page", itemList);
        return R.successWithData(2000, "success", pageMap);
    }

    @GetMapping("/item/{itemId}")
    public R<Item> getItemById(@PathVariable int itemId) {
        return R.successWithData(2000, "success", itemService.getItemById(itemId));
    }

    @PostMapping("/item/add")
    public R<Void> addItem(@RequestBody Item item) {
        if (itemService.addItem(item) > 0) {
            return R.success(2000, "success");
        } else {
            return R.fail(4000, "fail");
        }
    }

    @PostMapping("/item/modify")
    public R<Void> modifyItem(@RequestBody Item item) {
        if (itemService.modifyItem(item) > 0) {
            return R.success(2000, "success");
        } else {
            return R.fail(4000, "fail");
        }
    }

    @GetMapping("/item/remove/{itemId}")
    public R<Void> deleteItemById(@PathVariable int itemId) {
        if (itemService.removeItem(itemId) > 0) {
            return R.success(2000, "success");
        } else {
            return R.fail(4000, "fail");
        }
    }
}
