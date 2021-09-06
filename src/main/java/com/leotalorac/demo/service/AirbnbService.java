package com.leotalorac.demo.service;

import com.leotalorac.demo.model.Airbnb;
import com.leotalorac.demo.repository.AirbnbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirbnbService {
    private AirbnbRepository airbnbRepository;
    @Autowired
    public AirbnbService(AirbnbRepository repository){
        this.airbnbRepository=repository;
    }

    public Airbnb getReview(String id){
        return this.airbnbRepository.findById(id).orElse(null);
    }

    public Airbnb getReviewByName(String name){
        return this.airbnbRepository.findByName(name).orElse(null);
    }
    public void addReview(Airbnb airbnb){
        this.airbnbRepository.save(airbnb);
    }
}
