# Load-balancer
in this Project, a load balancer is created to manage the traffic and requests from users on the server. it distributes the load efficiently among different users by using Round Robin method for load balancing.

## Load Balancing for Content Distribution

## - Project Report-

# |The Load Balancing Strategy

We adopted Round Robin as a load balancing method. Round Robin is based on first-come,
first-served. According to the order, the load balancer continues to send requests to servers.
The first client, for example, will connect to Server 1, the second client to Server 2, and so
on. This distributes the server load equally, allowing it to manage huge traffic. Our designed
strategy was a combination of the Round Robin algorithm and the Random class, this way,
the Load Balancer will send requests to servers randomly limited by the range we defined for
requests and servers. The reason we used the Round Robin algorithm is because its load
balancing is easy to learn and apply and also because we have very limited knowledge on
the other load balancing methods. Our algorithm should be able to handle numerous client
requests at the same time. As a result, we employed threads so that each time a client
request came in, a new thread could be allocated to handle it. The LoadBalancer class runs
on a thread to allow several clients to access a web page at the same time.

# High-Level Approach|

TCP was the protocol that we utilized. The HTTP protocol is used by the server to transfer
the HTML file to the client. The HTTP program layer requests that the TCP layer establish the
connection and transmit the file. In the WebServer class, we set up the connection.
Our algorithm should be able to handle numerous client requests at the same time. As a
result, we employed threads so that each time a client request came in, a new thread could
be allocated to handle it. The LoadBalancer class runs on a thread to allow several clients to
access a web page at the same time.

# |Challenges We Have Faced:

At first, We were looking for a strategy to connect multiple clients to multiple servers

without using threads, and since we only knew how to connect one client to one server, it

was difficult to manage. After some research, we found some ways to implement that; the

first was using a List of servers and the second using Threads on the client side.Another
challenge was implementing the Load Balancer itself. We thought it had to be a Software

Load balancer like Nginx orHAProxy until we managedto code one that runs on a thread.


# Running The Project|

- We run the Main.java file (It creates the server andopens a socket connection
    to the server. When the server is ready, using Port 1234, it creates 4 sockets
    and these sockets start to listen for connections)
- The next step is to generate the Clients and to do this, we run the Client.java
    class which uses the ClientThread.java class ro create multiple client threads.
    For this test run, we will generate 5 clients. We are also sending the HTTP
    'Get' Request.
- Since we used a combination of Round Robin and Random, it redirects the
    servers in a random order as you can see in the screenshots above. It starts
    with socket 2004 instead of the traditional Round Robin way which is


supposed to start from 2001 and so on. We're establishing a connection,
creating a thread, and then redirecting to the client.

## (Group 20 )

## Balkisu Shehu Danmusa 218SE

## Mohanad Altarah 219SE


