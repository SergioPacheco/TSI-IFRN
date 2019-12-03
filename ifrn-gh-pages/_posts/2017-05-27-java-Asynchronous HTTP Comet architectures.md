---
title: "Tutorial java: Programando Sockets"
header:
  teaser: "assets/images/java-socket.jpg"
categories:
  - Java
tags:
  - Java
  - Comet
  - Http
---


> Comet has popularized asynchronous non-blocking HTTP programming, making it practically indistinguishable from reverse Ajax, also known as server push. In this article, Gregor Roth takes a wider view of asynchronous HTTP, explaining its role in developing high-performance HTTP proxies and non-blocking HTTP clients, as well as the long-lived HTTP connections associated with Comet. He also discusses some of the challenges inherent in the current Java Servlet API 2.5 and describes the respective workarounds deployed by two popular servlet containers, Jetty and Tomcat.

While Ajax is a popular solution for dynamically pulling data requests from the server, it does nothing to help us push data to the client. In the case of a Web mail application, for instance, Ajax would enable the client to pull mails from the server, but it would not allow the server to dynamically update the mail client. Comet, also known as server push or reverse Ajax, enhances the Ajax communication pattern by defining an architecture for pushing data from the server to the client. Comet enables us to push an event from the mail server to the WebMail client, which then signals the incoming mail.

Comet itself is based on creating and maintaining long-lived HTTP connections. Handling these connections efficiently requires a new approach to HTTP programming. In this article I introduce asynchronous, non-blocking HTTP programming and explain how it works. While I do present a Comet application at the end of the article, this style of programming is not restricted to Comet applications. Accordingly, this article describes asynchronous, non-blocking HTTP programming in general.

I start with an overview of client-based asynchronous message handling and message streaming, and then begin demonstrating the many uses of asynchronous HTTP on the server side. I explain the role and current limitations of the Java Servlet API 2.5, and demonstrate the use of the xSocket-http library to work around some of these limitations. The article concludes with a look at a dynamic Web application that leverages the two techniques associated with Comet architectures: long polling and streaming. I also show how this application could be implemented on Jetty and Tomcat, respectively.

Asynchronous message handling

At the message level, asynchronous message handling means that an HTTP client performs a request without waiting for the server response. In contrast, when performing a synchronous call, the caller thread is suspended until the server response returns or a timeout is exceeded. At the application level, code execution is stopped, waiting for the response before further actions can be taken. Client-side synchronous message handling is very easy to understand, as illustrated by the example in Listing 1.

## Listing 1. Client example -- synchronous call

```java
HttpClient httpClient = new HttpClient();
        
        // create the request message
        HttpRequest req = new HttpRequest("GET", "http://tools.ietf.org/html/rfc2616.html");

        // the call blocks until the response returns
        HttpResponse resp = httpClient.call(req);

        int status = resp.getStatus();
        // ...
```        
When performing an asynchronous call it is necessary to define a handler, which will be notified if the response returns. Typically, such a handler will be passed over by performing the call. The call method returns immediately. The application-level code instructions after the send statement will be processed without waiting for a server response. The server response will be handled by performing the handler's callback method. If the response returns, the network library will execute the callback method within a network-library-controlled thread. If necessary, the request message has to be synchronized with the response message at the application-code level. An asynchronous call is shown in Listing 2.

Listing 2. Client example -- asynchronous call

```java
HttpClient httpClient = new HttpClient();
        
        // response handler 
        IHttpResponseHandler responseHandler = new IHttpResponseHandler() {
                        
                public void onResponse(HttpResponse resp) throws IOException {
                        int status = resp.getStatus();
                        // ...
                }

                // ...
        };
        
        
        // create the request message
        HttpRequest req = new HttpRequest("GET", "http://tools.ietf.org/html/rfc2616.html");

        // send the request in an asynchronous way 
        httpClient.send(req, responseHandler);

        // ...


The advantage of this approach is that the caller thread will not be suspended until the response returns. Based on a good network library implementation, no outstanding threads are required. In contrast to the synchronous call approach, the number of outstanding requests is not restricted to the number of possible threads. The synchronous approach requires a dedicated thread for each concurrent request, which consumes a certain amount of memory. This can become a problem if you have many concurrent calls to be performed on the client side.

HTTP pipelining

Asynchronous message handling also enables HTTP pipelining, which you can use to send multiple HTTP requests without waiting for the server response to former requests. The response messages will be returned by the server in the same order as they were sent. Pipelining requires that the underlying HTTP connection is in persistent mode, which is the standard mode with HTTP/1.1. In contrast to non-persistent connections, the persistent HTTP connection stays open after the server has returned a response.

Pipelining can significantly improve application performance when fetching many objects from the same server. The implicit persistent mode eliminates the overhead of establishing a new connection for each new request, by allowing for the reuse of connections. Pipelining also eliminates the need for additional connection instances to perform concurrent requests.

Message content streaming

Asynchronous message handling can improve application performance by avoiding waiting threads, but another performance bottleneck arises when reading the message content.

It is not unusual for an HTTP message to contain kilobytes of content data. On the transport level, such larger messages will be broken down into several TCP segments. The TCP segment size is limited and depends on the underlying network and link layer. For Ethernet-based networks the maximum TCP segment size is up to 1460 bytes.

Bodyless HTTP messages such as GET requests don't contain body data. Often the size of such bodyless messages is smaller than 1 kilobyte. Listing 3 shows a simple HTTP request.

Listing 3. HTTP request

GET /html/rfc2616.html HTTP/1.1
Host: tools.ietf.org:80
User-Agent: xSocket-http/2.0-alpha-3
The correlating response of the request shown above contains a message body of 0.5 megabytes. On a personal Internet connection, the response message shown in Listing 4 would be broken into several TCP segments when sent.

Listing 4. HTTP response

HTTP/1.1 200 OK
Content-Length: 509497
Accept-Ranges: bytes
Last-Modified: Tue, 20 Nov 2007 03:10:57 GMT
Date: Sun, 03 Feb 2008 09:46:31 GMT
Content-Type: text/html; charset=US-ASCII
ETag: "d4026-7c639-9d13d240"
Server: Apache/2.2.6 (Debian) DAV/2 SVN/1.4.4 mod_python/3.3.1 Python/2.4.4 mod_ssl/2.2.6 OpenSSL/0.9.8g mod_perl/2.0.3 Perl/v5.8.8

```java
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=us-ascii" />
    <meta name="robots" content="index,follow" />
    <meta name="creator" content="rfcmarkup version 1.53" />
    <link rel="icon" href="/images/rfc.png" type="image/png" />
    <link rel="shortcut icon" href="/images/rfc.png" type="image/png" />
    <title>RFC 2616 Hypertext Transfer Protocol -- HTTP/1.1</title>  
  
[...]

 


</small></small></span>
</body></html>

```


Data transfer fragmentation can be hidden at the API level by accessing the body data as a steady and continuous stream. This approach, known as streaming, avoids the need to buffer large chunks of data before processing it. Streaming can also reduce the latency of HTTP calls, especially if both peers support streaming.

Using streaming allows the receiver to start processing the message content before the entire message has been transmitted. Often an HTTP message contains unstructured or semi-structured data, such as HTML pages, video, or music files, which will be processed immediately by the receiving peer. For instance, most browsers start rendering and displaying HTML pages without waiting for the complete page. For this reason most HTTP libraries support a stream-based API to access the message content.

In contrast to the body data, the message header contains well-structured data entries. To access the message header data most HTTP libraries provide dedicated and typed setter and getter methods. In most use cases the header can only be processed after the complete header has been received. The HTTP/1.1 specification doesn't define the order of the message headers, though it does state that it's a good practice to send general-header fields first, followed by request-header or response-header fields, and ending with the entity-header fields.

Streaming input data

To process received body data in a streaming manner, the receiving peer has to be notified immediately after the message header has been received. Based on the message header information, the receiver is able to determine the type of the received HTTP message, if body data exists, and which type of content the body contains.

The example code in Listing 5 (below) streams a returned HTML page into a file. The response message data will be processed as soon as it appears. Based on the retrieved body channel the FileChannel's transferFrom() implementation calls the body channel's read() method to transfer the data into the filesystem. This occurs in a blocking manner. If the socket read buffer is empty, the body channel's read() method will block until more data is received or the end-of-stream is reached. Blocking the read operation suspends the current caller thread, which can lead to inefficiency in system resource usage.

Listing 5. HTTP message example -- blocking input streaming

HttpClient httpClient = new HttpClient();

   HttpRequest req = new HttpRequest("GET", "http://tools.ietf.org/html/rfc2616.html");
   
   // returns immediately if the complete header (not message!) is received
   HttpResponse resp = httpClient.call(req);

   if (resp.getStatus() == 200) {  
      // create the output file 
      File file = new File("rfc2616.html");
      file.createNewFile();
      FileChannel fc = new RandomAccessFile(file, "rw").getChannel();

      // get a blocking message body channel
      ReadableByteChannel inputBodyChannel = resp.getBlockingBody();

      // and transfer the data
      fc.transferFrom(inputBodyChannel, 0, 900000);
      fc.close();
   }

   // ...
To process the message body in a non-blocking mode, a handler similar to the one seen in the asynchronous message calling example from Listing 2 can be used. In this case, a non-blocking body channel will be retrieved instead of a blocking channel. In contrast to the blocking channel the non-blocking channel's read() methods return immediately, whether data has been acquired or not. Notification support is required to avoid repeated, unsuccessful reads within a loop.

The BodyToFileStreamer of the example code in Listing 6 implements such a notification callback method. After retrieving the non-blocking body channel, the body handler will be assigned to the channel. The setDataHandler() call returns immediately. Setting the handler ensures that the body channel checks whether data is already available. If data is available, the handler's onData() method is run.

The callback method is also called each time body data is available. The network library takes a (pooled) worker thread to perform the callback method. This thread is only assigned to the body channel as long as the callback method is executed. For this reason no outstanding threads are required.

Listing 6. HTTP message example -- non-blocking input streaming

HttpClient httpClient = new HttpClient();

   HttpRequest req = new HttpRequest("GET", "http://tools.ietf.org/html/rfc2616.html");
   
   // returns immediately if the complete header (not message!) is received
   HttpResponse resp = httpClient.call(req);

   if (resp.getStatus() == 200) {
      // create the output file 
      final File file = new File("rfc2616.html");
      file.createNewFile();
      final FileChannel fc = new RandomAccessFile(file, "rw").getChannel();

      // get a non blocking message body channel
      NonBlockingBodyDataSource nbInputBodyChannel = resp.getNonBlockingBody();


      // data handler
      IBodyDataHandler bodyToFileStreamer = new IBodyDataHandler() {

         public boolean onData(NonBlockingBodyDataSource bodyChannel) {
            try {
               int available = bodyChannel.available();
   
               // data to transfer?
               if (available > 0) { 
                  bodyChannel.transferTo(fc, available);
   
               // end of stream reached?
               } else if (available == -1) {
                  fc.close();
               }
            } catch (IOException ioe) {
               file.delete();
            }
            return true;
         }

         // ...
      };


      // set the data handler 
      nbInputBodyChannel.setDataHandler(bodyToFileStreamer);
   }
 
   // ...


   Streaming output data

The streaming approach can also be used when sending message data. This avoids buffering large chunks of data. To do this, the message content will be transferred during the method call by using an InputStream or a ReadableByteChannel. After writing the message header, the body data will be transferred based on the body stream or channel. Listing 7 is an example of how implicit output streaming works. In this case the output streaming will be managed by the network library. Performing the HTTP call means that the user has to pass over a channel object, which represents the handle of a streamable resource.

Listing 7. Client example -- implicit output streaming

HttpClient httpClient = new HttpClient();
        
        // call request blocks until the response returns
        File file = new File("rfc2616.html");
        FileChannel fc = new RandomAccessFile(file, "r").getChannel();
                
        HttpRequest req = new HttpRequest("POST", "http://localhost:80/upload/rfc2616.html", "text/html", fc);

        // response handler 
        IHttpResponseHandler responseHandler = new IHttpResponseHandler() {
                        
           public void onResponse(HttpResponse resp) throws IOException {
              int status = resp.getStatus();
              // ...
           }

           // ...
        };

        // send the request by input streaming (this also works for the call method)
        httpClient.send(req, responseHandler);

        // ...
In some use cases the output (or body) streaming should be managed by application-level user code. An explicit, user-managed steaming approach requires that the user retrieves an output channel to write the body data. In Listing 8 a message header object is sent instead of a complete message object after the send() method has been called. This method call responds immediately by returning an output body channel object, which will be used by the application code to write the body data. The message-send procedure finalizes by calling the body channel's close() method.

Listing 8. Client example -- user-managed output streaming

HttpClient httpClient = new HttpClient();
        
        // create a http message header 
        HttpRequestHeader reqHdr = new HttpRequestHeader("POST", "http://localhost:80/upload/greeting", "text/plain");
        
        // response handler 
        IHttpResponseHandler responseHandler = new IHttpResponseHandler() {
                        
           public void onResponse(HttpResponse resp) throws IOException {
              int status = resp.getStatus();
              // ...
           }

           // ...
        };

        // sending the message header (instead of the complete message)
        WritableByteChannel outputBodyChannel = httpClient.send(reqHdr, responseHandler);

        // writing the message body data 
        outputBodyChannel.write(ByteBuffer.wrap(new byte[] { 45, 78, 56}));
        // ...

        // close the request
        outputBodyChannel.close();
Both approaches, streaming input data and streaming output data, will read and write data as soon as it appears. However, streaming doesn't mean that the data will be read or written directly to the network. All read and write operations work on internal socket buffers. When a write method is called, the operating system kernel transfers the data to the socket's send buffer. Returning from the write operation just says that the data has been copied to this low-level send buffer. It doesn't say that the peer has received the data.

Restrictions of the Java Servlet API 2.5

All of the examples in the previous sections show different ways of handling messages and content on the client side. As you will see later in Listing 10, it is possible to use the same programming style, as well as the same input and output message object representations, on the server side in a very seamless way. When you develop server-side HTTP-based applications, however, you must give consideration to the Java Servlet API.

The Servlet API defines a standard programming approach for handling HTTP requests on the server side. Unfortunately, the current Servlet API 2.5 supports neither non-blocking data streaming nor asynchronous message handling. When you implement a servlet's service method such as doPost() or doGet(), the application-specific servlet code will read the request data, perform the implemented business logic, and return the response. To simplify writing servlets, the Servlet API uses a single-threaded programming approach. The servlet developer doesn't have to deal with threading issues such as starting or joining threads. Thread management is part of the servlet engine's responsibilities. Upon receiving an HTTP request the servlet engine uses a (pooled) worker thread to call the servlet's service method.

Message handling

The downside of the Servlet API 2.5 is that it only allows for handling messages in a synchronous way. The HTTP request and response object have to be accessed within the scope of the request-handling thread. This message-handling approach is sufficient for most classic use cases. When you begin working with event-driven architectures such as Comet or middleware components such as HTTP proxies, however, asynchronous message handling becomes a very important feature.

When implementing an HTTP proxy, for instance, a request message has to be forwarded, and the response message has to be returned without wasting a request-handling thread for each open call. When you implement an HTTP proxy based on the current Servlet API, each open call requires one worker thread. The number of concurrent proxied connections is restricted to the number of possible worker threads.

Writing a synchronous HTTP proxy

Listing 9 shows an HTTP proxy based on the Servlet API. The servlet's doGet() method will be called each time a new GET request is received. After some proxy-related handling the request will be copied and forwarded using HttpClient. Upon receiving the correlating response some proxy-related handling will be performed and the HttpClient response message will be copied to the servlet response message. After leaving the doGet() method the servlet engine finalizes the response message.

Listing 9. Synchronous proxy example (GET request proxy)

public class ProxyServlet extends HttpServlet {
        
   private final HttpClient httpClient = new HttpClient();
   private String forwardHost;
   private String forwardPort;
        
        
   @Override
   public void init(ServletConfig config) throws ServletException {
      forwardHost = config.getInitParameter("forward.host");
      forwardPort = config.getInitParameter("forward.port");
   }
        
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                
      
      try 
      // compute the new url
      String uri = req.getRequestURI();
      if (req.getQueryString() != null) {
         uri += "?" + req.getQueryString();
      }
      uri = req.getScheme() + "://" + forwardHost + ":" + forwardPort + uri;
                 
      // handle proxy issues (hop-by-hop headers, cache, via header, ...)
      // ...
                 
      // create the forward request 
      HttpRequest forwardRequest = new HttpRequest(req.getMethod(), uri);
        
      // copy the request headers
      for (Enumeration<String> en = req.getHeaderNames(); en.hasMoreElements(); ) {
         String headername = en.nextElement();
         for (Enumeration<String> en2 = req.getHeaders(headername); en2.hasMoreElements(); ) {
            forwardRequest.addHeader(headername, en2.nextElement());
         }
      }
      forwardRequest.setHost(forwardHost + ":" + forwardPort);
                
      // forward the request in a synchronous manner
      HttpResponse response = httpClient.call(forwardRequest);
                
      // handle proxy issues (hop-by-hop headers, ...)
      // ...
                
      // copy the response headers
      response.removeHeader("Server");
      for (String headername : response.getHeaderNameSet()) {
         for (String headervalue : response.getHeaderList(headername)) {
            resp.addHeader(headername, headervalue);
         }
      }
      // copy the body (if exists)
      if (response.hasBody()) {
         byte[] body = response.getBlockingBody().readBytes();
         resp.getOutputStream().write(body);
      }
   }
}
Using the asynchronous HttpClient's send() method instead of the call() method won't help. The current Servlet API doesn't support writing requests out of the scope of the servlet request-handling thread. In essence, the Servlet 2.5 API is insufficient to write an asynchronous message-handling proxy.

Writing an asynchronous HTTP proxy

Writing an asynchronous message-handling proxy requires using an API other than the Servlet 2.5 specification. The HTTP proxy in Listing 10 is based on the same network library (xSocket-http) used in the previous client-side examples. xSocket-http is an extension module of the xSocket network library that supports HTTP programming on the server side, as well as the client side. The network library is independent of the Servlet API and does not implement a servlet container.

Whereas the Servlet API uses an HttpServletResponse object to send a response message, the xSocket-http network library uses an HttpResponseContext object. The xSocket-http network library doesn't pre-create a response message in an implicit way. Furthermore, in contrast to the Servlet API, neither the request object nor the response-context object is bound to the request-handling thread. Both artifacts can be accessed outside the network's library-managed threads.

Like the servlet's doGet() method, the ForwardHandler's onResponse() method will be called each time a request is received. After performing some proxy-related code the received request message will be forwarded using the asynchronous HttpClient's send() method. This method requires a response handler to handle the received response message. As you saw in Listing 2, using the HttpClient's send() method avoids the need for outstanding threads.

The most important aspect of this implementation is that the available threads don't restrict the number of concurrent proxied connections. The scalability of such an asynchronous proxy is only driven by the message-parsing cost and the capability to maintain the required system resources for an open TCP connection in an effective way. Each open TCP connection requires a certain number of socket buffers, control blocks, and file descriptors at the operating-system level.

Listing 10. Asynchronous proxy example

class ForwardHandler implements IHttpRequestHandler {
   private final HttpClient httpClient = new HttpClient();
   private String forwardHost;
   private int forwardPort;

   public ForwardHandler(String forwardHost, int forwardPort) {
      this.forwardHost = forwardHost;
      this. forwardPort = forwardPort;
   }

   public void onRequest(HttpRequest req, final IHttpResponseContext respCtx) throws IOException {
    
      // handle proxy issues (hop-by-hop headers, cache, via header, ...)
      // ...

      // update the target UTI (Host header will be update automatically)
      req.updateTargetURI(forwardHost, forwardPort);
  
      
      // create the response handler (timeout is not handled here)
      IHttpResponseHandler responseHandler = new IHttpResponseHandler() {

         @Execution(Execution.NONTHREADED)   // performance optimization
         public void onResponse(HttpResponse resp) throws IOException {
            // handle proxy issues (hop-by-hop headers, ...)
            // ...

            // return the response 
            respCtx.send(resp);
         }

         // ...
      };
      
      // .. and forward the request
      try {
         httpClient.send(req, responseHandler);
      } catch (ConnectException ce) {
         respCtx.sendError(502);
      }
   }
}


IServer proxy = new HttpServer(8080, new ForwardHandler("localhost", 80));
proxy.run();


Content streaming

The onResponse() and onRequest() methods in Listing 10 will be performed immediately after the message header is received. (The xSocket-http module also supports an InvokeOn annotation to specify if the callback method should be performed after receiving the message header or after receiving the complete message).

To reduce the required buffer sizes and to minimize call latency, the message body should be streamed. In Listing 10 this will be done implicitly by the xSocket-http module. The environment detects that an incomplete received message should be forwarded and registers a non-blocking forward handler on the incoming message-body channel.

Lower buffer sizes are required within the proxy because only parts of the message have to be buffered internally when it is transferred. Furthermore, the latency incurred by forwarding the message could be reduced significantly. If the message is forwarded in a non-streaming manner, first the whole message will be received and buffered before being forwarded. This adds the elapsed time between receiving the first and last message byte to the complete call latency.

Upload data streaming in Java Servlet API 2.5

Streaming is also supported by the current Servlet API, but only in a blocking way. When running the UploadServlet in Listing 11 in Tomcat (version 6.0.14, default configuration on Windows), the doPost() method will be called immediately after the request header is received. This allows you to stream the incoming message body. The UploadServlet reads some message-header entries and streams the message body into a file. If not enough data is available, the HttpServletRequest's input stream read() method will block. This means the request-handling thread will be suspended until more data is received.

Listing 11. Upload servlet example

class UploadServlet extends HttpServlet {
                
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        
      String requestURI = req.getRequestURI();

      if (requestURI.startsWith("/upload")) {
         String filename = requestURI.substring("/upload".length() + 1, requestURI.length());
         File file = new File("files" + File.separator + filename);
         file.createNewFile();

         FileOutputStream os = new FileOutputStream(file);
         InputStream is = req.getInputStream();
                                
         byte[] transferBytes = new byte[8196];
         int len;
         while ((len = is.read(transferBytes)) > 0) {
             os.write(transferBytes, 0, len);
         }
         os.close();
         is.close();
                                                                
      } else {
         resp.sendError(404);
      }
   }
}
Non-blocking upload data streaming

Outstanding threads can be avoided by using non-blocking streams. Listing 12 shows an UploadRequestHandler, which reads and transfers the incoming message body in a non-blocking way. Similar to the client-side non-blocking streaming example in Listing 6, a non-blocking body channel will be retrieved and a body-data handler will be set. After this operation the onRequest() method returns immediately, without sending a response message. If body data is received, the body-data handler will be called to transfer the available body data into a file. If the complete body is received, the response message will be sent.

Listing 12. Asynchronous, non-blocking server-side example

class UploadRequestHandler implements IHttpRequestHandler {
            
   public void onRequest(HttpRequest req, final IHttpResponseContext respCtx) throws IOException {

      String requestURI = req.getRequestURI();

      if (requestURI.startsWith("/upload")) {
         String filename = requestURI.substring("/upload".length() + 1, requestURI.length());
         final File file = new File("files" + File.separator + filename);
         file.createNewFile();
         
         final FileChannel fc = new RandomAccessFile(file, "rw").getChannel();


         IBodyDataHandler bodyToFileStreamer = new IBodyDataHandler() {

            public boolean onData(NonBlockingBodyDataSource bodyDataSource) {
               try {
                  int available = bodyDataSource.available();
     
                  if (available > 0) { 
                     bodyDataSource.transferTo(fc, available);
      
                  } else if (available == -1) {
                     fc.close();
                     respCtx.send(200);
                  }
               } catch (IOException ioe) {
                  file.delete();
                  respCtx.sendError(500);
               }
               return true;
            }

            //...
         };

         // set handler to stream the body into a file in a non-blocking manner 
         req.getNonBlockingBody().setDataHandler(bodyToFileStreamer);

      } else {
         respCtx.sendError(404);
      }        
   }
}


IServer server = new HttpServer(80, new UploadRequestHandler());
server.run();


The Comet pattern

Comet has popularized the asynchronous processing and non-blocking streaming of response data. Comet defines a Web architecture that enables the server to push events or data to the client without waiting for the client to request it. In essence, the Comet pattern is based on creating and maintaining long-lived HTTP connections.

Long-lived HTTP connections are required because the HTTP protocol is based on a request-response communication pattern. This means that data is only delivered to the client when specifically requested. An HTTP call is always initiated by the client. There is no direct way for the server to notify the client about events or to push data.

Polling is a simple solution to the need for server-initiated event notification. With polling, the client regularly asks the server if a new event has occurred. Based on the server response, the client is able to decide if further server requests are required. The event latency of this approach depends on the polling period. Increasing the polling rate reduces event latency. The downside of this is that frequent polling wastes system resources and scales poorly. Most polling calls return empty, with no new events having occurred.

The long poll is one of the two Comet strategies to reduce the frequency of HTTP calls. With a long poll, the HTTP call and the HTTP connection stay open until an event on the server side has occurred or a timeout is reached. Upon getting the response message on the client side, a new HTTP call will be opened immediately. Based on this approach the server is able to send events or to push data at any time. The downside is that open HTTP connections consume system resources such as socket buffers. Asynchronous message handling on both client and server side will help avoid outstanding threads.

The second Comet strategy is response body streaming. Like the long poll, the client in a streamed response implementation opens a long-running HTTP call. Instead of closing the HTTP connection after the response, the server keeps the response body stream open after sending the event or pushing the data. If further events occur on the server side, or data has to push, the open body stream will be used to transfer the data. Besides asynchronous message handling the client side should implement non-blocking input streaming to avoid outstanding threads. On the server side, user-managed output streaming is required to trigger the write operation on the application level.

An example of a Comet application

Listing 13 shows the JavaScript for an example Comet-style application that employs the forever frame, an iframe that receives script blocks and uses progressive rendering. The forever frame is one of several implementations that realize the streamed response strategy. That means the response body stream is closed after sending the response. The open body stream will be used to send the server-side triggered notifications.

The forever frame uses features of HTTP to incrementally deliver data through a hidden HTML iframe element. In Listing 13 only a paragraph ("text area") and a simple link will be printed out in the main browser window. Beside these visible elements, a hidden inline frame is also added to the page. Clicking the link sends a request to the server, which is answered by an (open body) response. Based on the target definition of the link the response will be handled in the context of the iframe. Instead of sending regular HTML text elements the server returns a JavaScript block. Because the browse renders HTML pages incrementally, the script block is executed as it is received. By returning a JavaScript block like parent.printTime("Sun Feb 10 10:05:20 CET 2008") the JavaScript function printTime() will be executed, which sets the paragraph content with the given time.

Listing 13. Forever Frame -- JavaScript example

<html>
  <head>
    <script language="Javascript">
      function printTime(time) {
         document.getElementById('text').innerHTML = time; 
      }
    </script>
  </head> 

  <body>
    <iframe id="iframe" name="iframe" width ="0" height ="0" border="0">
    </iframe>
    
    <a href ="time" target="iframe"/>start timer</a>
    <p id="text">0</p>
  </body>
</html>
Comet on the server side

As already mentioned, the client-side request will be answered by the server with an open-body response message. On the server side a JavaScript block is sent periodically to update the time on the browser page. Similar to Listing 8 a message header object is passed over after the send() method has been called. The returned body channel is then used to write the JavaScript block by a TimerTask.

Listing 14. Forever Frame -- server example

class ForeverFrameHandler implements IHttpRequestHandler {
                
   private final Timer timer = new Timer("timer", true);
        
        
   public void onRequest(HttpRequest req, IHttpResponseContext respCtx) throws IOException {
  
      // handle the time request
      if (req.getRequestURI().endsWith("/time")) {
                
         // write the message header by retrieving the body handle
         final BodyDataSink outChannel = respCtx.send(new HttpResponseHeader(200, "text/html"));

         // timer task definition                  
         TimerTask timerTask = new TimerTask() {
                
            public void run() {
               try {
                  String script = "<script>\r\n" +
                                  "  parent.printTime(\"" + new Date().toString() + "\");\r\n" +
                                  "</script>";
                  outChannel.write(script);
               } catch (IOException ioe) {
                  cancel();
                  try {
                     outChannel.close();
                  } catch (IOException ignore) { }
               }
            }      
         };
         
         
         // start the timer task 
         timer.schedule(timerTask, 0, 1000);
      }
      
      //..
   }
}

IServer server = new HttpServer(8080, new ForeverFrameHandler());
server.run();
Chunking

The message body content in Listing 14 is written using chunked transfer encoding. This feature of the HTTP/1.1 specification makes it possible to send content data without knowing when the end of the message will be reached. Chunked transfer encoding, or chunking, breaks the message content down into a series of blocks of data, which can be transmitted in chunks. Each chunk starts with a leading chunk size field. The zero size chunk indicates the end of the message, as you can see in Listing 15.

Listing 15. Chunked HTTP response

HTTP/1.1 200 OK
Content-Type: text/html; charset=iso-8859-1
Transfer-Encoding: chunked
Server: xSocket-http/2.0-alpha-3

47
<script>
  parent.printTime("Sun Feb 10 10:05:20 CET 2008");
</script>
47
<script>
  parent.printTime("Sun Feb 10 10:05:22 CET 2008");
</script>
47
<script>
  parent.printTime("Sun Feb 10 10:05:23 CET 2008");
</script>

[...]

47
<script>
  parent.printTime("Sun Feb 10 12:25:04 CET 2008");
</script>
47
<script>
  parent.printTime("Sun Feb 10 12:25:05 CET 2008");
</script>
0



Comet in Jetty 6 and Tomcat 6

Comet support is one of the key features of the upcoming Servlet API 3.0. In the meantime, popular servlet engines already support Comet, using different approaches. Jetty 6 tries to keep the impact on the existing Servlet API programming approach to a minimum. Jetty uses continuations to avoid blocking threads by writing the response data. The continuation suspends the request and frees the current servlet request-handling thread. Jetty uses a RetryRequest runtime exception to implement the continuation.

When the suspend() method is called in Listing 16 below, Jetty throws a RetryRequest runtime exception. This RetryRequest exception is caught by the container, and the request placed into a timeout queue. If the timeout expires, the whole service method of the servlet will be re-executed. In this sense a Jetty continuation is not a "true" continuation: a classic continuation represents an execution state of a program at a certain point. The execution state is defined by the program's store and control state which includes the stack trace, variables, and program counter. Resuming the execution means that the program's store and control state will be reconstructed and the processing will continue. The Jetty approach implies that the service method is idempotence, which means it yields the same result after the method is applied multiple times.

Listing 16. Forever Frame -- Jetty-based server example

public class ForeverFrameJettyServlet extends HttpServlet {

   public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
      // handle the time request
      if (req.getRequestURI().endsWith("/time")) {
                        
         // get the jetty continuation
         Continuation cc = ContinuationSupport.getContinuation(req, null);
       
         // set the header
         resp.setContentType("text/html");
                            
         // write time periodically
         while (true) {
            cc.suspend(1000); // suspend the response
            String script = "<script>\r\n" +
                            "  parent.printTime(\"" + new Date() + "\");\r\n" +
                            "</script>";
            resp.getWriter().println(script);
            resp.getWriter().flush();
         }
      }

      // ...       
   }
}
Tomcat 6 introduces a new CometProcessor interface, which defines an event() callback method to process "Comet events" in an asynchronous way. The event() method will be called if specific events like BEGIN, ERROR, or READ occur. Implementing the CometProcessor interface means that Servlet API service methods such as doGet() will never be called.

Listing 17. Forever Frame -- Tomcat-based server example

public class ForeverFrameTomcatServlet extends HttpServlet implements CometProcessor  {
  
   private ArrayList<HttpServletResponse> connections = new ArrayList<HttpServletResponse>();
 
 
   public void init() throws ServletException {
      Thread timeSenderThread = new Thread(new TimeSender());
      timeSenderThread.setDaemon(true);
      timeSenderThread.start();
   }
 
         
   public void event(CometEvent event) throws IOException, ServletException {
      HttpServletRequest req = event.getHttpServletRequest();
      HttpServletResponse resp = event.getHttpServletResponse();
 
                 
      // handle the time request
      if (req.getRequestURI().endsWith("/time")) {

         if (event.getEventType() == CometEvent.EventType.BEGIN) {
            synchronized(connections) {
               connections.add(resp);
            }     
                            
         } else if (event.getEventType() == CometEvent.EventType.ERROR) {
             synchronized(connections) {
               connections.remove(resp);
            }
            resp.getWriter().close();
            event.close();
                                 
         } else if (event.getEventType() == CometEvent.EventType.END) {
            synchronized(connections) {
               connections.remove(resp);
            }
            resp.getWriter().close();
            event.close();                                  
         } 
      } 
      
      // ...
   }


   class TimeSender implements Runnable {
                 
      public void run() {
         while (true) {
            synchronized (connections) {
               for (HttpServletResponse response : connections) {
                  try {
                     PrintWriter bodyStream = response.getWriter();
                     String script = "<script>\r\n" +
                                     "  parent.printTime(\"" + new Date() + "\");\r\n" +
                                     "</script>";
                     bodyStream.write(script);
                     bodyStream.flush();
                  } catch (IOException e) {
                     // handle the exception
                  }
               }
            }
            try {
               Thread.sleep(1000);
            } catch (InterruptedException ignore) { }
         }
      }
   }
}
While beyond the scope of this article, it should also be noted that Grizzly, the NIO framework on which the Glassfish HTTP Listener is built, also handles Comet events. In contrast to Tomcat, Grizzly's Comet event handling is integrated with the servlet's service methods.

In conclusion

Servlet containers Tomcat and Jetty take two different approaches to asynchronous, non-blocking HTTP, but both provide adequate support. In addition, most popular HTTP and network libraries already support asynchronous message handling, at least at an early alpha-level implementation. The Apache Software Foundation's HttpCore library supports non-blocking HTTP for the client and server side, and is currently in beta status. The upcoming version of the Apache HttpClient is also based on the HttpCore library. The MINA project is currently working on Asyncweb, which also supports non-blocking HTTP on the client and server side. Examples in this article were based on the HTTP module of the xSocket network library, which is in alpha status.

See the Resources section to learn more about the technologies discussed in this article. You can also discuss them in the associated discussion forum.

Learn more about this topic

"
COMET - The next stage of AJAX " (Alex Russell, IrishDev.com, March 2006) introduced the term Comet to describe the event-driven, server-push data streaming pattern discussed in this article.
"New features added to Servlet 2.5" (Jason Hunter, JavaWorld, January 2006) continues the author's JavaWorld series of articles documenting the evolution of the Java Servlet API.
"Master Merlin's new I/O classes" (Michael Nygard, JavaWorld, September 2001) introduces the java.nio packages and their premise: to allow Java applications to handle thousands of open connections while delivering scalability and performance.
"Architecture of a highly scalable NIO-based server" (Gregor Roth, Java.net, February 2006) describes the fundamental patterns that are used to realize the architecture of a connection-oriented NIO-based server.
"Mastering Ajax: Write scalable Comet applications with Jetty and Direct Web Remoting" (Philip McCarthy, IBM developerWorks, July 2007) further introduces the Comet pattern and Jetty 6's Continuations API.
"Scripting on the Java platform" (Gregor Roth, JavaWorld, November 2007) features a non-blocking, multithreaded SMTP server example written using JRuby and Java.
xSocket-http is an extension module of the NIO-based network library xSocket.
The documentation for the current HTTP specification  HTTP/1.1 also includes a definition of chunked transfer encoding.
JSR 315: JavaServlet 3.0 Specification documents the current state of Java Servlet API 3.0
The Jetty documentation Wiki includes a page on how Jetty 6 uses continuations to work around the limitations of the Java Sevlet API 2.5.
"Advanced IO and Tomcat" describes Tomcat's support for asynchronous message handling and Comet.
See the Grizzly project homepage to learn more about Grizzly's approach to Comet.
You'll find good, short explanations of HTTP pipelining as well as Comet strategies such as long polling, streaming, and the forever frame implementation on Wikipedia.
See the JavaWorld Development Tools Research Center for more about tools, frameworks, and APIs for Java development.
Also see Network World's IT Buyer's Guides: Side-by-side comparison of hundreds of products in over 70 categories.




