package com.sample.app.library.dtos.user;

import lombok.Data;

import java.util.Date;
@Data
public class LoginDTO {
    public String uuid;
    public String username;
    public String password;
    public String md5;
    public String sha1;
    public Date registered;
}
