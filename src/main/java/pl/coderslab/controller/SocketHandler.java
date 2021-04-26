package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UsersStatus;
import pl.coderslab.services.ChatService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class SocketHandler extends TextWebSocketHandler {

    @Autowired
    ChatService chatService;

    private List<WebSocketSession> sessions;


    public SocketHandler(List<WebSocketSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("new user connected");
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
            chatService.addMessageToDB(message.getPayload());

            for (WebSocketSession user : sessions) {
                user.sendMessage(new TextMessage(message.getPayload()));

        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        for (WebSocketSession user : sessions) {
            if (user.equals(session)) {
                sessions.remove(user);
                user.close();
            }
        }
    }

}
