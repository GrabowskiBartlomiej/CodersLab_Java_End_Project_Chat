package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UsersStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;
    private final RoomDao roomDao;

    public UserService(UserDao userDao, RoomDao roomDao) {
        this.userDao = userDao;
        this.roomDao = roomDao;
    }


    public User loginAttempt(String email, String password){
        return userDao.login(email, password);

    }

    public void loginAndSetSession(User user, HttpServletRequest req){
        user.setStatus(3);
        userDao.update(user);

        UsersStatus us = userDao.getUsersStatus(userDao.findAllUsersOnTheServer(1));

        req.getSession().setAttribute("usersOnline", us.getOnline());
        req.getSession().setAttribute("usersOffline", us.getOffline());

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("rooms", user.getRooms());
    }


    public boolean checkPasswords(String password, String repeatedPassword){
        return repeatedPassword.equals(password);
    }

    public void successRegistration(String password, String email, String username, HttpServletRequest req){
        password = userDao.hashPassword(password);
        System.out.println(password);
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);
        List<Room> rooms = new ArrayList<>();
        rooms.add(roomDao.findById(1));
        user.setRooms(rooms);
        user.setStatus(0);
        userDao.addUser(user);
        req.getSession().setAttribute("username", username);
    }

    public void logout(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
        user.setStatus(0);
        userDao.update(user);
        req.getSession().removeAttribute("user");
    }

    public void logoutAll(){
        List<User> allUsers = userDao.findAll();
        for(User user : allUsers){
            user.setStatus(0);
            userDao.update(user);
        }
    }
}
