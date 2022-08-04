package com.perficient.Lab.Information.System.Controller;

import com.perficient.Lab.Information.System.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/LIS")
public class loginController {

    @RequestMapping(value = "/LIS/login",method = { RequestMethod.GET, RequestMethod.POST })
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model,
                                   Authentication authentication) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login.html";
    }

    @RequestMapping(value="/LIS/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/LIS/login?logout=true";
    }

    @RequestMapping(value = "/LIS/login/checkIsApprove",method = { RequestMethod.GET, RequestMethod.POST })
    public String approvepage(Model  model) {
        model.addAttribute("errorMessge", "Please Wait, Your request hasn't been Approved Yet!!");
        return "login.html";
    }
}
