package com.hby.crudbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("item")
public class Item {

    @TableId(value = "itemId", type = IdType.AUTO)
    Integer itemId;
    String name;
    String description;
}
