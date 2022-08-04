package com.perficient.Lab.Information.System.Controller;

import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/LIS")
public class UserRequestController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/allUsers")
    public String viewallUsers(Model model) {
        List<Users> user = usersService.getAllUsers();
        model.addAttribute("user", user);
        return "alluserslist.html";
    }

    @GetMapping("/pending-users-request")
    public String viewallPendingUsersRequests(Model model) {
        List<Users> user = usersService.findIsapproved(false);
        model.addAttribute("user", user);
        return "userpendingrequest.html";
    }

    @GetMapping("/user-Approve/{id}")
    public String userApprove(@PathVariable("id") int id){
        usersService.approve(id);
        return "redirect:/LIS/pending-users-request";
    }

    @GetMapping("/role-admin/{id}")
    public String admin(@PathVariable("id") int id){
        usersService.admin(id);
        return "redirect:/LIS/pending-users-request";
    }

    @GetMapping("/user/pending/delete/{id}")
    public String deletePandingUser(@PathVariable("id") int userId, RedirectAttributes ra){

        try {
            usersService.deleteUser(userId);

        }
        catch(Exception e){
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/LIS/pending-users-request";


    }
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId, RedirectAttributes ra){

        try {
            usersService.deleteUser(userId);

        }
        catch(Exception e){
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/LIS/allUsers";


    }
}
