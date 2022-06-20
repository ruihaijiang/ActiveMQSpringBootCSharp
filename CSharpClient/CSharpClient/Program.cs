using System;

using System.Threading;
using Apache.NMS;
using Apache.NMS.Util;
using Apache.NMS.ActiveMQ;
using Newtonsoft.Json;

namespace CSharpClient
{
    internal class Program
    {
        static void Main(string[] args)
        {


            Uri connecturi = new Uri(JMS_URL);

            Console.WriteLine("About to connect to " + connecturi);

            // NOTE: ensure the nmsprovider-activemq.config file exists in the executable folder.
            IConnectionFactory factory = new ConnectionFactory(connecturi);

            using (IConnection connection = factory.CreateConnection())
            using (ISession session = connection.CreateSession())
            {

                IDestination nodestatus_destination = SessionUtil.GetDestination(session, NODE_STATUS_TOPIC_NAME);
                IDestination orderchanged_destination = SessionUtil.GetDestination(session, ORDER_CHANGED_TOPIC_NAME);
                IDestination stock_changed_destination = SessionUtil.GetDestination(session, STOCK_CHANGED_TOPIC_NAME);
                IDestination heartbeat_destination = SessionUtil.GetDestination(session, HEARTBEAT_QUEUE_NAME);

                Console.WriteLine("Using destination: " + nodestatus_destination);

                // Create a consumer and producer
                using (IMessageConsumer nodestatus_consumer = session.CreateConsumer(nodestatus_destination))       /*node-status*/
                using (IMessageConsumer order_changed_consumer = session.CreateConsumer(orderchanged_destination))  /*order-changed*/
                using (IMessageProducer stock_changed_producer = session.CreateProducer(stock_changed_destination)) /*stock-changed*/
                using (IMessageProducer heartbeat_producer = session.CreateProducer(heartbeat_destination))         /*heart-beat*/
                {

                    connection.Start();
                    stock_changed_producer.DeliveryMode = MsgDeliveryMode.NonPersistent;
                    stock_changed_producer.RequestTimeout = receiveTimeout;

                    heartbeat_producer.DeliveryMode = MsgDeliveryMode.NonPersistent;
                    heartbeat_producer.RequestTimeout = receiveTimeout;

                    nodestatus_consumer.Listener += new MessageListener(OnNodeStatusMessage);
                    order_changed_consumer.Listener += new MessageListener(OnOrderChangedMessage);


                    for (int i = 0; i < 100; i++)
                    {
                        Thread.Sleep(1000 * 10);


                        Heartbeat heartbeat = new Heartbeat();
                        heartbeat.SendTime = DateTime.Now;
                        heartbeat.NodeId = "CsharpClient";
                        heartbeat.Status = 2;
                        string text = JsonTool.SerializeObject(heartbeat);
                        Console.WriteLine("send heartbeat message:" + text);
                        // Send a message
                        ITextMessage request = session.CreateTextMessage(text);
                        heartbeat_producer.Send(request);



                        StockChanged store_changed = new StockChanged();
                        store_changed.SendTime = DateTime.Now;
                        store_changed.StockId = "stock-12345";
                        store_changed.ChangeTime = DateTime.Now;
                        string text2 = JsonTool.SerializeObject(store_changed);

                        // Send a message
                        Console.WriteLine("send StoreChanged message:" + text2);
                        ITextMessage request2 = session.CreateTextMessage(text2);
                        stock_changed_producer.Send(request2);

                    }

                }
            }
        }

        protected static void OnNodeStatusMessage(IMessage receivedMsg)
        {
            ITextMessage message = receivedMsg as ITextMessage;
            Console.WriteLine("OnNodeStatusMessage() received message:" + message.Text);
            string text = message.Text;
            NodeStatus obj = JsonConvert.DeserializeObject<NodeStatus>(text, JsonTool.settings);
            Console.WriteLine("OnNodeStatusMessage() received object:" + obj.ToString());

        }

        protected static void OnOrderChangedMessage(IMessage receivedMsg)
        {

            ITextMessage message = receivedMsg as ITextMessage;
            Console.WriteLine("OnOrderChangedMessage() received message:" + message.Text);
            string text = message.Text;
            OrderChanged obj = JsonConvert.DeserializeObject<OrderChanged>(text, JsonTool.settings);
            Console.WriteLine("OnOrderChangedMessage() received object:" + obj.ToString());


        }



        //static public String JMS_URL = "activemq:tcp://localhost:61616";
        static public String JMS_URL = "activemq:tcp://192.168.1.5:61616";

        static public String NODE_STATUS_TOPIC_NAME = "topic://node-status";
        static public String ORDER_CHANGED_TOPIC_NAME = "topic://order-changed";
        static public String STOCK_CHANGED_TOPIC_NAME = "topic://stock-changed";
        static public String HEARTBEAT_QUEUE_NAME = "queue://heart-beat";


        protected static TimeSpan receiveTimeout = TimeSpan.FromSeconds(10);
    }
}
