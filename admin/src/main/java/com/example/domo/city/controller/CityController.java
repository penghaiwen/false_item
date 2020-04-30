package com.example.domo.city.controller;

import com.example.domo.city.entity.City;
import com.example.domo.city.services.ICityService;
import com.exception.RestBean;
import com.exception.RestException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/aaa")
@RestController
@Api(tags = "城市")
public class CityController {
    @Resource
    private ICityService cityService;

    @GetMapping("get")
    public RestBean login(){
        City city=cityService.getCityById(1L);
        return RestBean.ok(city);

    }


    @GetMapping("get2")
    public RestBean get2(){
        City city=cityService.getCityById(1L);
        return RestBean.ok(city);
    }
}
