package com.leotalorac.demo.repository;

import com.leotalorac.demo.model.Airbnb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirbnbRepository extends MongoRepository<Airbnb,String> {
    Optional<Airbnb> findByName(String name);
}
