package pl.coderslab.sockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpOutputMessage;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;
import pl.coderslab.entity.Message;
//import pl.coderslab.sockets.ChatServer;
//import pl.coderslab.sockets.ChatServer;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class SocketHandler extends TextWebSocketHandler {

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

        String incMessage = message.getPayload();

        System.out.println(incMessage);

//        ChatServer.broadcast();


        TextMessage returnMessage = new TextMessage(message.getPayload());

        for(WebSocketSession user : sessions){
            user.sendMessage(returnMessage);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        for(WebSocketSession user : sessions){
            if(user.equals(session)){
                sessions.remove(user);
                user.close();

            }
        }
    }
}
