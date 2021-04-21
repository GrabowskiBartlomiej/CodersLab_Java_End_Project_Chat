package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import pl.coderslab.controller.SocketHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Bean
    public List<WebSocketSession> sessions() {
        return new ArrayList<>();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(socketController(), "/chat");
    }

    @Bean
    public WebSocketHandler socketController() {
        return new SocketHandler(sessions());
    }
}
