package com.yunfan.gmoft.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 10:41
 */
public class SystemWebSocketHandler implements WebSocketHandler {

	private static Logger logger = Logger.getLogger("log.webSocket");

    private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	try {
	    	System.out.println("Start   .... ");
	        logger.debug("connect to the websocket success......");
	        users.add(session);
	        String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
	        if(userName!= null){
	            session.sendMessage(new TextMessage("12"));
	        }
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	System.out.println(message.getPayloadLength());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
