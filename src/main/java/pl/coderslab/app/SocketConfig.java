package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;
import pl.coderslab.sockets.SocketHandler;

import java.util.ArrayList;
import java.util.List;
//import pl.coderslab.sockets.ChatServer;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer{

    @Bean
    public List<WebSocketSession> sessions(){
        return new ArrayList<>();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler( socketController(),"/chat");
    }

    @Bean
    public WebSocketHandler socketController(){
        return new SocketHandler(sessions());
    }
}
