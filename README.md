# Overview

- The Server: A Springboot application with an embedded ActiveMQ broker.
- Springboot Client: A Springboot application sends and receives messages.
- C# Client:  A C# application sends and receives messages.


```                                                                    
 ┌───────────────────────┐     ┌───────────────────────────────┐     ┌───────────────────────┐
 │  Springboot Client    │     │    Springboot Server          │     │    C# Client          │
 │                       │     │                               │     │                       │
 │                       │     │       [NodeStatusProducer]    │     │                       │
 │                       │     │                │              │     │                       │
 │                       │     │  ┌─────────────│───────────┐  │     │                       │
 │                       │     │  │             │           │  │     │                       │
 │                       │     │  │    ┌─────── V ───────┐  │  │     │                       │
 |  [NodeStatusConsumer]<───────────── │ Topic1:         │────────────>[NodeStatusConsumer]  │
 │                       │     │  │    │ 'node-status'   │  │  │     │                       │
 │                       │     │  │    └─────────────────┘  │  │     │                       │
 │                       │     │  │    ┌─────────────────┐  │  │     │                       │
 |[OrderChangedProducer]─────────────> │ Topic2:         │────────────>[OrderChangedConsumer]│
 │                       │     │  │    │'order-changed'  │  │  │     │                       │
 │                       │     │  │    └─────────────────┘  │  │     │                       │ 
 │                       │     │  │    ┌─────────────────┐  │  │     │                       │
 |[SockChangedConsumer] <───────────── │ Topic3:         │<─────────── [StockChangedProducer]│
 │                       │     │  │    │ 'stock-changed' │  │  │     │                       │
 │                       │     │  │    └─────────────────┘  │  │     │                       │
 │                       │     │  │    ┌─────────────────┐  │  │     │                       │
 |  [HeatbeatProducer]  ─────────────> │ Queue1:         │<──────────── [HeatbeatProducer]   │
 │                       │     │  │    │ 'heartbeat'     │  │  │     │                       │
 │                       │     │  │    └─────────────────┘  │  │     │                       │
 │                       │     │  │[Embedded Broker] │      │  │     │                       │ 
 │                       │     │  └──────────────────│──────┘  │     │                       │
 │                       │     │                     │         │     │                       │
 │                       │     │                     V         │     │                       │
 │                       │     │        [Heatbeat Consumer]    │     │                       │
 └───────────────────────┘     └───────────────────────────────┘     └───────────────────────┘
```           

# Queue: "heartbeat"
Both the Springboot Client and the C# Client keep sending the heartbeat message to the queue "heartbeat".

The Server receives and handles the heartbeat message.


# Topic: "node-status"
The Server sends the node status message to the topic "node-status". Both the Springboot Client and the C# Client receive messages on this topic.

Use curl to send a RESTful request to the server and the server will send the node status message.

```
curl -X POST "http://localhost:8091/test/SendStatus?nodeId=node1&status=2"
```


# Topic: "order-changed"
The Springboot Client publishes the order-changed message on topic "order-changed".
The C# Client receives messages on this topic.

# Topic: "stock-changed"
The C# Client publishes the stock-changed message on topic "stock-changed".
The Springboot Client receives messages on this topic.