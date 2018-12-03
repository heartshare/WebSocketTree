# WebSocketTree
websocket通信

<pre>
WebSocket是HTML5出的协议，与HTTP协议没有关系，HTTP不支持持久连接（长连接，循环连接不算）

Websocket与Http同样建立于tcp传输协议之上，通过tcp传输层进行数据传输。
发起握手：
       每个websocket连接都始于http连接。
       具体来说，websocket协议在第一次握手时，通过HTTP协议在传输数据，但是
    比普通HTTP请求相比多了一些字段。

服务器响应：
       根据特殊的请求头进行了特殊的响应，首先101返回码表明本次连接的通信
    协议经过了转换并成功握手成功建立了通信，connection字段和upgrade字段
    字段则表明本次通信协议进行了升级转换，转换的是websocket协议。

Websocket被广泛用于web的实时消息通信系统中。
它实现了浏览器与服务器全双工通信，将会替代基于http的ajax长轮询的拉取消息模式。

Websocket是HTML5下一种新的协议，它实现了浏览器与服务器全双工通信，能更好的
节省服务器资源和带宽并达到实时通信的目的，它与HTTP一样通过已建立的TCP连接来
传输数据，但是它和HTTP最大不同是：
    Websocket是一种双向通信协议，在建立连接后，Websocket服务器和客户端
  都能主动向对方发送或者接受数据，就像socket一样。
    Websocket需要像TCP一样，先建立连接，连接成功后才能相互通信。

相比HTTP长连接，建立连接后客户端与服务端是完全平等的，可以互相主动请求，而
HTTP长连接基于HTTP，是传统的客户端对服务器发起请求的模式。

HTTP长连接中，每次数据交换除了真正的数据部分外，服务器和客户端还要大量交换
HTTP header，信息交换效率低下.Websocket协议通过第一个request建立了TCP
连接之后，之后交换的数据都不需要HTTP header就能交换数据。这显然和原有的
HTTP协议有区别，所以它需要对服务器和客户端呢都进行升级才能实现，不同的URL
可以复用同一个WebSocket连接等功能，这些都是HTTP长连接不能做到的。

建立websocket连接后，只要客户端和服务器端任意一端不主动断开连接，通信行为
都是在一个持久连接上发起，后续数据与请求都通过帧序列的形式进行传输。

HTTP 1.1也支持长连接，在一个TCP连接上可以传送多个HTTP请求和响应，减少了
建立和关闭连接的消耗和延迟，但仍然还是无状态的。

websocket设计上天生为HTTP增强通信，将会在即时IM通信，游戏等领域得到更广泛的利用。

在客户端，new WebSocket实例化一个新的WebSocket客户端对象，请求类似 
  ws://yourdomain:port/path的服务端WebSocket URL,客户端WebSocket
对象会自动解析并识别为WebSocket请求，并连接服务端端口，执行双方握手过程
</pre>
