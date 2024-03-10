package com.sample.app.library.dtos.user;

import lombok.Data;

@Data
public class UserDTO {
    public int id;
    public String firstname;
    public String lastname;
    public String email;
    public String birthDate;
    public LoginDTO login;
    public AddressDTO address;
    public String phone;
    public String website;
    public CompanyDTO company;
}
