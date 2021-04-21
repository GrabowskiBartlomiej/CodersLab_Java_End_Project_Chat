package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.dao.ChannelDao;
import pl.coderslab.dao.MessageDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.*;
import pl.coderslab.repository.MessageRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private ChannelDao channelDao;
    private RoomDao roomDao;
    private UserDao userDao;
    private MessageDao messageDao;
    private MessageRepository messageRepository;

    public ChatService(ChannelDao channelDao, RoomDao roomDao, UserDao userDao, MessageDao messageDao, MessageRepository messageRepository) {
        this.channelDao = channelDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.messageRepository = messageRepository;
    }

    public String addNewRoom(String roomName, String roomLogo, HttpServletRequest req) {
        List<Channel> channels = new ArrayList<>();
        User user = (User) req.getSession().getAttribute("user");

        Channel channel = new Channel();
        channel.setName("General");
        channel.setType("Text");
        channelDao.addChannel(channel);
        channels.add(channel);

        Room room = new Room();
        room.setName(roomName);
        room.setChannels(channels);

        if (!roomLogo.isEmpty()) {
            room.setLogo(roomLogo);
        }

        roomDao.addRoom(room);

        List<Room> userRooms = user.getRooms();

        userRooms.add(room);
        user.setRooms(userRooms);
        userDao.update(user);

        return room.getId() + "/" + channel.getId();
    }


    public void addMessageToDB(String fullMessage) {
        long channelId = getChannelIdFromMessage(fullMessage);
        long userId = getUserIdFromMessage(fullMessage);
        String message = getMessageContent(fullMessage);

        Message message1 = new Message();
        User user = userDao.findById(userId);
        Channel channel = channelDao.findById(channelId);
        String[] noName = message.split(": ");
        message1.setContent(message.replace(noName[0] + ":", ""));
        message1.setChannel(channel);
        message1.setUser(user);
        LocalDateTime time = LocalDateTime.now();
        message1.setPostTime(time);

        messageDao.addMessage(message1);
    }



    public long getChannelIdFromMessage(String message) {
        String[] parts = message.split("\\s+");
        long id = Long.parseLong(parts[0]);
        return id;
    }

    public long getUserIdFromMessage(String message) {
        String[] parts = message.split("\\s+");
        long id = Long.parseLong(parts[1]);
        return id;
    }

    public String getMessageContent(String message) {
        String[] parts = message.split(":", 2);
        return message.replace(parts[0] + ":", "");
    }


    public void changeRoomAndChannel(String roomId, String channelId, HttpServletRequest req) {
        long id1 = Long.parseLong(roomId);
        long id2 = Long.parseLong(channelId);
        Room currentRoom = roomDao.findById(id1);

        List<Channel> channels = currentRoom.getChannels();
        req.getSession().setAttribute("channels", channels);
        req.getSession().setAttribute("roomName", currentRoom.getName());
        req.getSession().setAttribute("roomId", roomId);
        req.getSession().setAttribute("channelId", channelId);
        req.getSession().setAttribute("channelName", channelDao.findById(id2).getName());

        long chId = Long.parseLong(channelId);
        req.getSession().setAttribute("messages", messageRepository.findAllByChannelId(chId));

        UsersStatus us = userDao.getUsersStatus(userDao.findAllUsersOnTheServer(id1));
        req.getSession().setAttribute("usersOnline", us.getOnline());
        req.getSession().setAttribute("usersOffline", us.getOffline());
    }

    public String firstChannel(String roomId) {
        List<Channel> channels = roomDao.findById(Long.parseLong(roomId)).getChannels();
        long channelId = channels.get(0).getId();
        String address = roomId + "/" + channelId;
        return address;
    }

}


