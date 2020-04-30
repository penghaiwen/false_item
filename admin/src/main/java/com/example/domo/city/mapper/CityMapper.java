package com.example.domo.city.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domo.city.entity.City;
import org.apache.ibatis.annotations.Param;

public interface CityMapper extends BaseMapper<City> {

    City getCityById(@Param("id") Long id);
}
