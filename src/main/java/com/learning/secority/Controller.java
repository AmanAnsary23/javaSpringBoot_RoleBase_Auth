package com.learning.secority;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hey")
    public String hey() {
        return "Hey world!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
