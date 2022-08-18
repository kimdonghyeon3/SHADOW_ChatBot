package com.example.shadow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS(); //웹 소캣을 사용하기 위해 설정하는 부분
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 받을 API의 Client를 설정 -> shadow 챗봇
        registry.setApplicationDestinationPrefixes("/app"); //prefix 설정
        // 구독한 곳에서(우리 UI) 챗봇 API에게 메시지를 전달
        registry.enableSimpleBroker("/topic"); //topic 이라는 주제에 브로커를 설정(해당 api를 구독하고 있는 client에게 메시지 전달)
    }
}