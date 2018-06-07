//package com.tim.tinder.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.converter.MessageConverter;
//import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
//import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
//import org.springframework.messaging.simp.config.ChannelRegistration;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketMessageConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/webSocket/chat")
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }
//
//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) {
//
//    }
//
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
//
//    }
//
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
//
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
//
//    }
//
//    @Override
//    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
//
//    }
//
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> list) {
//        return false;
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/chat");
//    }
//}