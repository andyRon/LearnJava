package com.andyron.chapter2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andy Ron
 */

@Controller
public class HelloController {

    @RequestMapping("/index")
    public String sayHello() {
        return "index";
    }
}
