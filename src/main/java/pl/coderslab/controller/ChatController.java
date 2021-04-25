package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.services.ChatService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {
    @Autowired
    ChatService chatService;


    @RequestMapping("/chat/{roomId}/{channelId}")
    public String channel(HttpServletRequest req, @PathVariable String roomId, @PathVariable String channelId) {
        chatService.changeRoomAndChannel(roomId, channelId, req);
        return "chat";
    }

    @RequestMapping("/chat/{roomId}")
    public String room(@PathVariable String roomId) {
        String address = chatService.firstChannel(roomId);
        return "redirect:/chat/" + address;
    }

    @RequestMapping(value = "/chat/addRoom", method = RequestMethod.GET)
    public String formRoom() {
        return "addRoom";
    }

    @RequestMapping(value = "/chat/addRoom", method = RequestMethod.POST)
    public String addRoom(@RequestParam String roomName, @RequestParam String roomLogo, HttpServletRequest req) {
        String address = chatService.addNewRoom(roomName, roomLogo, req);
        return "redirect:/chat/" + address;
    }

    @PostMapping("/addChannel/{id}")
    public String addChannel(@RequestParam String channelName, @PathVariable long id) {
        long newChId = chatService.addNewChannel(channelName, id);
        return "redirect:/chat/" + id + "/" + newChId;
    }

    @PostMapping("/changeLogo/{rId}/{chId}")
    public String changeLogo(@RequestParam String logoUrl, @PathVariable long rId, @PathVariable long chId, HttpServletRequest req) {
        chatService.changeLogo(rId, logoUrl, req);
        return "redirect:/chat/" + rId + "/" + chId;
    }

}
