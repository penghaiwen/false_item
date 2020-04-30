package com.example.domo.city.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domo.city.entity.City;
import com.example.domo.city.mapper.CityMapper;
import com.example.domo.city.services.ICityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Override
    public City getCityById(Long id) {
        return baseMapper.getCityById(id);
    }
}
