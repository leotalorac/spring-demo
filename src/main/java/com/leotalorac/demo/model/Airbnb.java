package com.leotalorac.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "listingsAndReviews")
public class Airbnb {
    @Id
    private String _id;
    private String listing_url;
    private String name;
    private String description;
}
