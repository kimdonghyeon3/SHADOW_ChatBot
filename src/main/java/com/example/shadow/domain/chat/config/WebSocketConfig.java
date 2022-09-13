package com.example.shadow.domain.chat.config;

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
        registry.addEndpoint("/ws").withSockJS(); // 처음 웹소켓 handshake를 위한 경로
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 받을 API의 Client를 설정 -> shadow 챗봇 (/app은 가공처리가 필요할 경우 생성)
        registry.setApplicationDestinationPrefixes("/app"); //prefix 설정
        // 구독한 곳에서(우리 UI) 챗봇 API에게 메시지를 전달 -> 가공처리가 끝나 전달받은 메시지를 /topic을 구독하는 사람들에게 전달(/topic은 발신자가 메시지를 보낼 곳을 의미)
        registry.enableSimpleBroker("/topic"); //topic 이라는 주제에 브로커를 설정(해당 api를 구독하고 있는 client에게 메시지 전달)
    }
}

// 우리는 내장 메시지 브로커를 통해 메시지의 그룹을 담고 있는 /topic에서 메시지 브로커가 이를 처리한다.
// 메시지 핸들러에서 라우팅되는 prefix를 파라미터로 지정. -> 메시지 가공을 위한 곳으로 라우팅한다.(shadow에서 가공하기 때문에 /app으로 보내 가공한다.)

// 전체 구조(Spring 서버에서 보낸 메세지(/shadow) -> 메시지를 받고 보낼 곳 설정(/topic) -> websocket handshake으로 연결된 /topic으로 전송 -> /topic에서 받은 메시지 가공을 위해 /app으로 이동
// ->
