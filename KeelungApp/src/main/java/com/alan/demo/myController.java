package com.alan.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)

public class myController {
    @GetMapping("/SightAPI?zone={input}")
    public Sight[] getSight(@PathVariable("input") String input) {
        KeelungSightsCrawler kl = new KeelungSightsCrawler();
        return kl.getItems(input);
    }

    @GetMapping("/test")
    public String test() {
        return "this is a test";
    }
}
