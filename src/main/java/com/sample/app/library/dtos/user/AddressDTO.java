package com.sample.app.library.dtos.user;

import lombok.Data;

@Data
public class AddressDTO {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public GeoDTO geo;
}
