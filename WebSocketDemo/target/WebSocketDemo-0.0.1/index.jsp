<script type="text/javascript" src="http://localhost:8080/js/sockjs-0.3.min.js"></script>

<script>
    var websocket;
    
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/WebSocketDemo/webSocketServer");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/WebSocketDemo/webSocketServer");
    } else {
        websocket = new SockJS("http://localhost:8080/WebSocketDemo/sockjs/webSocketServer");
    }
    
    websocket.onopen = function (evnt) {
    };
    
    websocket.onmessage = function (evnt) {
        document.getElementById("myP").innerHTML = evnt.data
    };
    
    websocket.onerror = function (evnt) {
    };
    
    websocket.onclose = function (evnt) {
    }

</script>
<html>
<body>
<p style="color: red; text-align:center; font-size:100px"  id = "myP" >start...</p> 
</body>
</html>