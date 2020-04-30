package com.example.domo.city.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("mapper/city")
public class City {
    private Long id;
    private String name;
}
