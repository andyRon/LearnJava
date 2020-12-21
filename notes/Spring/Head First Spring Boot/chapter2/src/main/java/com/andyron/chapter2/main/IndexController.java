package com.andyron.chapter2.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Andy Ron
 */
@Controller
public class IndexController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
