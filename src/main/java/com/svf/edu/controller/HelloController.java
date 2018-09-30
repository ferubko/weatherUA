package com.svf.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by stepanferubko
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String welcome(Map<String, Object> model) {
        model.put("message", "Hello");
        return "welcome";
    }
}
