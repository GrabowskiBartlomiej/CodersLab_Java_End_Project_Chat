package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dao.ChannelDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Channel;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UsersStatus;
import pl.coderslab.repository.MessageRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChatController {
    private final UserDao userDao;
    private final RoomDao roomDao;
    private final ChannelDao channelDao;
    private final MessageRepository messageRepository;

    public ChatController(UserDao userDao, RoomDao roomDao, ChannelDao channelDao, MessageRepository messageRepository) {
        this.userDao = userDao;
        this.roomDao = roomDao;
        this.channelDao = channelDao;
        this.messageRepository = messageRepository;
    }

    @RequestMapping("/chat/{id}")
    public String chat(HttpServletRequest req, @PathVariable String id) {

        long roomId = Long.parseLong(id);
        Room currentRoom = roomDao.findById(roomId);

        List<Channel> channels = currentRoom.getChannels();

        req.getSession().setAttribute("channels", channels);
        req.getSession().setAttribute("roomName", currentRoom.getName());
        req.getSession().setAttribute("roomId", roomId);
        req.getSession().setAttribute("usersList", userDao.findAllUsersOnTheServer(roomId));
        req.getSession().removeAttribute("channelName");


        UsersStatus us = userDao.getUsersStatus(userDao.findAllUsersOnTheServer(roomId));
        req.getSession().setAttribute("usersOnline", us.getOnline());
        req.getSession().setAttribute("usersOffline", us.getOffline());

        return "chat";
    }

    @RequestMapping("/chat/{roomId}/{channelId}")
    public String channel(HttpServletRequest req, @PathVariable String roomId, @PathVariable String channelId) {

        long id1 = Long.parseLong(roomId);
        long id2 = Long.parseLong(channelId);
        Room currentRoom = roomDao.findById(id1);

        List<Channel> channels = currentRoom.getChannels();
        req.getSession().setAttribute("channels", channels);
        req.getSession().setAttribute("roomName", currentRoom.getName());
        req.getSession().setAttribute("roomId", roomId);
        req.getSession().setAttribute("channelId", channelId);
        req.getSession().setAttribute("channelName", channelDao.findById(id2).getName());
        User currentUser = (User) req.getSession().getAttribute("user");
        long chId = Long.parseLong(channelId);
        req.getSession().setAttribute("messages", messageRepository.findAllByUserIdAndChannelId(currentUser.getId(),chId) );

        UsersStatus us = userDao.getUsersStatus(userDao.findAllUsersOnTheServer(id1));
        req.getSession().setAttribute("usersOnline", us.getOnline());
        req.getSession().setAttribute("usersOffline", us.getOffline());

        return "chat";
    }


}
