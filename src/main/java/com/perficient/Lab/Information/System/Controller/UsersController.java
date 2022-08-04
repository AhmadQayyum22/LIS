package com.perficient.Lab.Information.System.Controller;

import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/LIS/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(UsersController.class);


    @RequestMapping(value ="/register",method = { RequestMethod.GET})
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new Users());
        return "register.html";
    }

    @PostMapping("/process_register")
    public String registration(@Valid @ModelAttribute("user") Users users, Errors error){
        if(error.hasErrors()){
            log.error("Registeration form validation failed due to : " + error.toString());
            return "register.html";
        }
        usersService.save(users);
        return "redirect:/LIS/login";

    }

}
