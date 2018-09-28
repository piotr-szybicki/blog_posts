package com.example.dealership.query.datamodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "CarOffersQuick")
public class CarOfferQuickDescriptionDTO implements Serializable {
    @Id
    public String id;

    @NotBlank
    @Size(max = 140)
    public String make;

    @NotBlank
    @Size(max = 140)
    public String model;

    public CarOfferQuickDescriptionDTO() {
    }

    public CarOfferQuickDescriptionDTO(String id, @NotBlank @Size(max = 140) String make, @NotBlank @Size(max = 140) String model) {
        this.id = id;
        this.make = make;
        this.model = model;
    }
}
