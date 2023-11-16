package br.com.smashcode.babycare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloRestController {
    @GetMapping
    public String sayHello() {
        return "<h5>Hello Baby Care 👶🩺💜</h5>";
    }
}
