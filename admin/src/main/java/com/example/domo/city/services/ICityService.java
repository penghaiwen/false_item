package com.example.domo.city.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domo.city.entity.City;

public interface ICityService  extends IService<City> {

    City getCityById(Long id);
}
