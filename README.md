# Overview


```                                                                    
 ┌─  ──────────────────────┐     ┌──────────────────────────────────┐      ┌────────────────────────┐
 │   Springboot Client     │     │     Springboot Server            │      │    C# Client           │
 │                         │     │                                  │      │                        │
 │                         │     │        [NodeStatusProducer]      │      │                        │
 │                         │     │                 │                │      │                        │
 │                         │     │   ┌─────────────│────────────┐   │      │                        │
 │                         │     │   │             │            │   │      │                        │
 │                         │     │   │    ┌─────── V ───────┐   │   │      │                        │
 |   [NodeStatusConsumer] <────────────── │ Topic1:         │ ──────────────>[NodeStatusConsumer]   │
 │                         │     │   │    │ 'node-status'   │   │   │      │                        │
 │                         │     │   │    └─────────────────┘   │   │      │                        │
 │                         │     │   │    ┌─────────────────┐   │   │      │                        │
 | [OrderChangedProducer] ──────────────> │ Topic2:         │ ──────────────>[OrderChangedConsumer] │
 │                         │     │   │    │'order-changed'  │   │   │      │                        │
 │                         │     │   │    └─────────────────┘   │   │      │                        │ 
 │                         │     │   │    ┌─────────────────┐   │   │      │                        │
 | [SockChangedConsumer]  <────────────── │ Topic3:         │ <───────────── [StockChangedProducer] │
 │                         │     │   │    │ 'stock-changed' │   │   │      │                        │
 │                         │     │   │    └─────────────────┘   │   │      │                        │
 │                         │     │   │    ┌─────────────────┐   │   │      │                        │
 |   [HeatbeatProducer]   ──────────────> │ Queue1:         │ <────────────── [HeatbeatProducer]    │
 │                         │     │   │    │ 'heart-beat'    │   │   │      │                        │
 │                         │     │   │    └─────────────────┘   │   │      │                        │
 │                         │     │   │[Embedded Broker] │       │   │      │                        │ 
 │                         │     │   └──────────────────│───────┘   │      │                        │
 │                         │     │                      │           │      │                        │
 │                         │     │                      V           │      │                        │
 │                         │     │         [Heatbeat Consumer]      │      │                        │
 │                         │     │                                  │      │                        │
 └─────────────────────────┘     └──────────────────────────────────┘      └────────────────────────┘

```            


# Server                                                               
The server starts the ActiveMQ broker.
The sever also has some RESTful interfaces. Some tests can be carried out via these RESTful interfaces.


Server: Java/Spring boot