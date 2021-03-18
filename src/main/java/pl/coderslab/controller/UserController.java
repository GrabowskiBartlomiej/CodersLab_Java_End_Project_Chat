package pl.coderslab.controller;

import com.mysql.cj.PreparedQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller

public class UserController {

    private final UserDao userDao;
    private final RoomDao roomDao;

    public UserController(UserDao userDao, RoomDao roomDao) {
        this.userDao = userDao;
        this.roomDao = roomDao;
    }

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView HomePage() {
        ModelAndView mav = new ModelAndView("home");//if logged return homeloged.jsp else homepage
       // ModelAndView mav = new ModelAndView("chat");
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









            User user = userDao.login(email,password);



            req.getSession().setAttribute("user",user);
            req.getSession().setAttribute("rooms",roomDao.findAll());







            return "redirect:/chat/1";
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
            User user = new User();
            user.setPassword(password);
            user.setEmail(email);
            user.setUsername(username);
            List<Room> rooms = new ArrayList<>();
            rooms.add(roomDao.findById(1));
            user.setRoomsAccess(rooms);
            userDao.addUser(user);
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
