package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;
    private final UserService userService;

    public HomeController(HomeService homeService, UserService userService){
        this.homeService = homeService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Model model){
        User user = userService.getUser();
        if(user == null){
            return "login";
        }
        model = homeService.reloadModelWithData(model);

        return "home";
    }

}
