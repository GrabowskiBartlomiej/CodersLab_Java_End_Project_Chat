package pl.coderslab.controller;

import com.mysql.cj.PreparedQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    public String logged(@RequestParam String email,@RequestParam String password, HttpServletRequest req){
        if(userDao.login(email,password) == null){
            return "login";
        }else{
            req.getSession().setAttribute("user",userDao.login(email,password));
            return "homeloged";
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register");
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String re_password, HttpServletRequest req) {

        if (re_password.equals(password)) {
            password = userDao.hashPassword(password);
            System.out.println(password);
            User user = new User(email, username, password);
            userDao.addAuthor(user);
            req.getSession().setAttribute("username",username);
            return "successRegister";
        } else {
            return "register";
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ("home");
    }



}
