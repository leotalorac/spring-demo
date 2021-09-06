package com.leotalorac.demo.controller;

import com.leotalorac.demo.model.Airbnb;
import com.leotalorac.demo.service.AirbnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("airbnb")
public class AirbnbController {
    private AirbnbService airbnbService;

    @Autowired
    public AirbnbController(AirbnbService service){
        this.airbnbService=service;
    }

    @GetMapping("/review/{id}")
    public Airbnb getReviewById(@PathVariable("id") String id){
        return this.airbnbService.getReview(id);
    }

    @GetMapping("/review/name/{name}")
    public Airbnb getReviewByName(@PathVariable("name") String name){
        return this.airbnbService.getReview(name);
    }
    @PostMapping("/review/add")
    public void addReview(@RequestBody Airbnb airbnb){
        this.airbnbService.addReview(airbnb);
    }
}
