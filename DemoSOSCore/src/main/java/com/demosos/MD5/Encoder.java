package com.demosos.MD5;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class Encoder {
    public static String encrypt(String aPassword) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(aPassword, null);
    }
}
