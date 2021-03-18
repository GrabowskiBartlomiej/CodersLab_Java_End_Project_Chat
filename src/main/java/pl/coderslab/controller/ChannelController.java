package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dao.ChannelDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.entity.Channel;

@Controller

public class ChannelController {

    private final ChannelDao channelDao;

    public ChannelController(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    @RequestMapping("/addChannels")
    public String addChannels(){
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

}
