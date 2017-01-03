var websocket;
var hostname = window.location.host;
var datacenter = new Array();
function initWebSocket(socket_flag){
	var params="?flag="+socket_flag;
	try{
		if ('WebSocket' in window) {
			websocket = new WebSocket("ws://"+hostname+"/WebSocketDemo/webSocketServer.do" + params);
		} else if ('MozWebSocket' in window) {
			websocket = new MozWebSocket("ws://"+hostname+"/WebSocketDemo/webSocketServer.do" + params);
		} else {
			websocket = new SockJS("/WebSocketDemo/sockjswebSocketServer.do" + params);
		}
	}catch(e){
		debugger;
	}
}