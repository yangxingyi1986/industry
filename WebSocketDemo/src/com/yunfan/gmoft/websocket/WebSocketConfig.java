package com.yunfan.gmoft.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 10:31
 */
@Configuration
@EnableWebSocket
@Controller
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(),"/webSocketServer.do").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor());

        registry.addHandler(systemWebSocketHandler(), "/sockjswebSocketServer.do").setAllowedOrigins("*").addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
        
        MessageDisplayerEndPoint.startListener();
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}
