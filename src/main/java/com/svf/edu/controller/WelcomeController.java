package com.svf.edu.controller;

import com.svf.edu.service.BotClientService;
import com.svf.edu.vo.BotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by stepanferubko
 */
@Controller
public class WelcomeController {
    private final static Logger LOG = Logger.getLogger(WelcomeController.class.getName());

    @Autowired
    private BotClientService botClientService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home1(Model model) {

        List<BotClient> allChatUsers = botClientService.getAllChatUsers();

        model.addAttribute("size", allChatUsers.size());
        model.addAttribute("lists", allChatUsers);
        return "/home";
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        LOG.info("Home page");
        model.addAttribute("msg", "Hello there, This message has been injected from the controller method");
        return "/home";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about() {
        LOG.info("About page");
        return "/about";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        LOG.info("Login page");
        return "/login";
    }

    @RequestMapping(path = "/403", method = RequestMethod.GET)
    public String error403() {
        LOG.info("Error page");
        return "/error/403";
    }


}
