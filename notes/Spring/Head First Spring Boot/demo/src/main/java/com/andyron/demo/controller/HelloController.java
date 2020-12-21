package com.andyron.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andy Ron
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String syaHello() {
        return "index";
    }
}
