package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.dao.UserDao;


@Controller

public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView HomePage() {
        ModelAndView mav = new ModelAndView("home");//if logged return homeloged.jsp else homepage
        return mav;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView logged(){
        return new ModelAndView("homeloged");
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register");
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(){
        return null;
    }




}
