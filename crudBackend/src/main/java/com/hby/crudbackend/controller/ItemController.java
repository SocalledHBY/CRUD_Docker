package com.hby.crudbackend.controller;

import com.hby.crudbackend.entity.R;
import com.hby.crudbackend.entity.Item;
import com.hby.crudbackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public R<List<Item>> getAllItems() {
        return R.successWithData(2000, "success", itemService.getAllItems());
    }

    @GetMapping("/item/{itemId}")
    public R<Item> getItemById(@PathVariable("itemId") int itemId) {
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
    public R<Void> deleteItemById(@PathVariable("itemId") int itemId) {
        if (itemService.removeItem(itemId) > 0) {
            return R.success(2000, "success");
        } else {
            return R.fail(4000, "fail");
        }
    }
}
