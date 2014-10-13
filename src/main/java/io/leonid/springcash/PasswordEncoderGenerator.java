package io.leonid.springcash;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by leonid on 02.10.2014.
 */
public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        String rawPass = "apple";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(rawPass);

        System.out.println("Original password: " + rawPass);
        System.out.println("Hashed password: " + hashedPass);
    }
}
