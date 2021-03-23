package pl.coderslab.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.dao.ChannelDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Channel;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller

public class RoomController {
    private final RoomDao roomDao;
    private final ChannelDao channelDao;
    private final UserDao userDao;

    public RoomController(RoomDao roomDao,ChannelDao channelDao,UserDao userDao) {
        this.roomDao = roomDao;
        this.channelDao = channelDao;
        this.userDao = userDao;
    }




    @RequestMapping(value = "/chat/addRoom", method = RequestMethod.GET)
    public ModelAndView formRoom(){
        return new ModelAndView("addRoom");
    }

    @RequestMapping(value = "/chat/addRoom", method = RequestMethod.POST)
    public String addRoom(@RequestParam String roomName, @RequestParam String roomLogo, HttpServletRequest req){

        List<Channel> channels = new ArrayList<>();
        Channel channel = new Channel();
        channel.setName("General");
        channel.setType("Text");
        channelDao.addChannel(channel);
        channels.add(channel);

        Room room = new Room();
        room.setName(roomName);
        room.setChannels(channels);
        if(!roomLogo.isEmpty()){
            room.setLogo(roomLogo);
        }

        roomDao.addRoom(room);




        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);
        List<Room> rooms = user.getRooms();
        rooms.add(room);
        user.setRooms(rooms);
        userDao.update(user);

        Room room2 = rooms.get(rooms.size()-1);
        long roomId = room2.getId();

        return "redirect:/chat/"+roomId;
    }



}
