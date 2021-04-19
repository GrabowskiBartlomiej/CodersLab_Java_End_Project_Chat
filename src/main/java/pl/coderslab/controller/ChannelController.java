package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.dao.ChannelDao;
import pl.coderslab.dao.MessageDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Channel;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class ChannelController {

    private final ChannelDao channelDao;
    private final MessageDao messageDao;
    private final UserDao userDao;


    public ChannelController(ChannelDao channelDao, MessageDao messageDao, UserDao userDao) {
        this.channelDao = channelDao;
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @RequestMapping("/addChannels")
    public String addChannels() {
        Channel ch1 = new Channel();
        ch1.setName("General");
        ch1.setType("Text");

        channelDao.addChannel(ch1);

        Channel ch2 = new Channel();
        ch2.setName("NSFW");
        ch2.setType("Text");

        channelDao.addChannel(ch2);
        return ("/addRoom");
    }



    public void addMessage(@RequestParam long roomId, @RequestParam long userId, @RequestParam long channelId, @RequestParam String message, HttpSession session) {
        Message message1 = new Message();
        User user = userDao.findById(userId);
        Channel channel = channelDao.findById(channelId);
        message1.setContent(message);
        message1.setChannel(channel);
        message1.setUser(user);
        LocalDateTime time = LocalDateTime.now();
        message1.setPostTime(time);

        messageDao.addMessage(message1);

    }


}
