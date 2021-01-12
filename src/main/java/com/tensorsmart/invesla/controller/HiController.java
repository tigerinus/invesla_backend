package com.tensorsmart.invesla.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HiController {

    @GetMapping
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Hi! This is invesla.\n");

    }
}
