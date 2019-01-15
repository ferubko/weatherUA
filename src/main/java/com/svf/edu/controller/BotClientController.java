package com.svf.edu.controller;

import com.svf.edu.service.BotClientService;
import com.svf.edu.vo.BotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by stepanferubko
 */
@Controller
public class BotClientController {
    @Autowired
    private BotClientService botClientService;

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getWeather() {
        List<BotClient> allChatUsers = botClientService.getAllChatUsers();
        ModelAndView modelAndView = new ModelAndView("admin", "allChatUsers", allChatUsers);
        return modelAndView;
    }

    @RequestMapping(value = "/edituser/{id}", method = RequestMethod.GET)
    public String empeditform(@PathVariable long id, Model model) {
        BotClient botClient = botClientService.findBotClient(id);
        if (botClient == null) {
            return "/home";
        }
        model.addAttribute("botClient", botClient);
        return "/edituser";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("userForm") @Valid BotClient botClient, Model model) {
        botClientService.updateBotClient(botClient);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("botClient", botClient);
        return modelAndView;
    }
}
