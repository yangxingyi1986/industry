package com.yunfan.gmoft.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 15:40
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	private static Logger logger = Logger.getLogger( "log.webSocket" );
	
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
                > attributes) throws Exception {
    	try {
	        if (request instanceof ServletServerHttpRequest) {
	            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
	            HttpServletRequest httpReq = servletRequest.getServletRequest();
	            String flag=httpReq.getParameter("flag");
//	            HttpSession session = httpReq.getSession(false);
//	            if (session != null) {
	                //使用userName区分WebSocketHandler，以便定向发送消息
//	                String userName = (String) session.getAttribute(Constants.SESSION_USERNAME);
	                if (StringUtils.isEmpty(flag)){
	                	flag = "default";
	                }
	                attributes.put(Constants.WEBSOCKET_USERNAME,flag);
//	            }
	        }
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
