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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        User user = userDao.findById(((User) req.getSession().getAttribute("user")).getId());

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
        List<Room> unique = userRooms.stream().distinct().collect(Collectors.toList());
        user.setRooms(unique);
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
        User user = userDao.findById(((User) req.getSession().getAttribute("user")).getId());
        UsersStatus us = userDao.getUsersStatus(userDao.findAllUsersOnTheServer(id1));
        List<Room> rooms = user.getRooms();
        List<Room> unique = rooms.stream().distinct().collect(Collectors.toList());
        List<Channel> channels = currentRoom.getChannels();
        List<User> online = us.getOnline().stream().distinct().collect(Collectors.toList());
        List<User> offline = us.getOffline().stream().distinct().collect(Collectors.toList());
        List<User> usersOnTheChannel = userDao.findAllUsersOnTheServer(id1);
        List<User> allUsers = userDao.findAll();

        req.getSession().setAttribute("channels", channels);
        req.getSession().setAttribute("roomName", currentRoom.getName());
        req.getSession().setAttribute("roomId", roomId);
        req.getSession().setAttribute("channelId", channelId);
        req.getSession().setAttribute("channelName", channelDao.findById(id2).getName());
        req.getSession().setAttribute("messages", messageRepository.findAllByChannelId(id2));
        req.getSession().removeAttribute("rooms");
        req.getSession().setAttribute("rooms", unique);
        req.getSession().setAttribute("usersOnline", online);
        req.getSession().setAttribute("usersOffline", offline);

        for (Iterator<User> it = allUsers.iterator(); it.hasNext(); ) {
            User next = it.next();
            for (int i = 0; i < usersOnTheChannel.size(); i++) {
                if (next.getId() == usersOnTheChannel.get(i).getId()) {
                    it.remove(); //metoda remove() iteratora
                    break;
                }
            }
        }

        req.getSession().setAttribute("allUsers", allUsers);
        req.getSession().setAttribute("user", user);
    }

    public String firstChannel(String roomId) {
        List<Channel> channels = roomDao.findById(Long.parseLong(roomId)).getChannels();
        long channelId = channels.get(0).getId();
        String address = roomId + "/" + channelId;
        return address;
    }

    public long addNewChannel(String channelName, long id) {
        Channel channel = new Channel();
        channel.setName(channelName);
        Room room = roomDao.findById(id);
        List<Channel> channels = room.getChannels();
        channels.add(channel);
        room.setChannels(channels);
        channelDao.addChannel(channel);
        roomDao.update(room);
        return channel.getId();
    }

    public void changeLogo(long roomId, String logoUrl, HttpServletRequest req) {
        Room room = roomDao.findById(roomId);
        User user = (User) req.getSession().getAttribute("user");
        List<Room> rooms = user.getRooms();
        int index;
        for (Room ro : rooms) {
            if (ro.getId() == room.getId()) {
                index = rooms.indexOf(ro);
                rooms.set(index, room);
            }
        }
        room.setLogo(logoUrl);
        roomDao.update(room);
        user.setRooms(rooms);
        userDao.update(user);
        req.getSession().setAttribute("user", user);
    }

    public void addUsers(int[] usersToAdd, long rId) {

        Room room = roomDao.findById(rId);
        System.out.println(room);

        for (int user : usersToAdd) {
            User user1 = userDao.findById(user);
            System.out.println(user1);
            List<Room> rooms = user1.getRooms();
            System.out.println(rooms);
            List<Room> unique = rooms.stream().distinct().collect(Collectors.toList());
            System.out.println(unique);
            unique.add(room);
            System.out.println(unique);
            user1.setRooms(unique);
            userDao.update(user1);

        }
    }

    public void changeRoomName(String name, long rId) {
        Room room = roomDao.findById(rId);
        room.setName(name);
        roomDao.update(room);
    }

    public void leaveRoom(long rId, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        List<Room> rooms = user.getRooms();

        for (Iterator<Room> it = rooms.iterator(); it.hasNext(); ) {
            Room next = it.next();
            if (next.getId() == rId) {
                it.remove(); //metoda remove() iteratora
            }
        }

        List<Room> unique = rooms.stream().distinct().collect(Collectors.toList());
        user.setRooms(unique);
        userDao.update(user);
    }


    public void renameChannel(String name, long chId) {
        Channel ch = channelDao.findById(chId);
        ch.setName(name);
        channelDao.update(ch);
    }

    public void deleteChannel(long rId, long chId) {
        Room room = roomDao.findById(rId);
        List<Channel> channels = room.getChannels();

        for(Iterator<Channel> it = channels.iterator(); it.hasNext();){
            Channel nextCh = it.next();
            if(nextCh.getId() == chId){
                it.remove();
                channels.remove(nextCh);
                channels = channels.stream().distinct().collect(Collectors.toList());
                room.setChannels(channels);
                roomDao.update(room);
                channelDao.delete(nextCh);
            }
        }
    }
}
