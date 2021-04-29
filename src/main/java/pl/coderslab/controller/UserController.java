package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.services.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/")
    public String HomePage() {
        return "home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logged(@RequestParam String email, @RequestParam String password, HttpServletRequest req) {
        User user = userService.loginAttempt(email, password);

        if (user == null) {
            return "login";
        } else {
            userService.loginAndSetSession(user, req);


            return "redirect:/chat/1/1";
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String re_password, HttpServletRequest req) {
        if (userService.checkPasswords(password, re_password)) {
            userService.successRegistration(password, email, username, req);
            return "successRegister";
        } else {
            return "register";
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        userService.logout(req);
        return ("home");
    }


    @PostMapping("/changeUserName/{rId}/{cId}")
    public String changeUserName(HttpServletRequest req, @RequestParam String name, @PathVariable long rId, @PathVariable long cId) {
        userService.changeUsername(name, req);
        return "redirect:/chat/" + rId + "/" + cId;
    }

    @PostMapping("/changeUserAvatar/{rId}/{cId}")
    public String changeUserAvatar(HttpServletRequest req, @RequestParam String link, @PathVariable long rId, @PathVariable long cId) {
        userService.changeUserAvatar(link, req);
        return "redirect:/chat/" + rId + "/" + cId;
    }

}
