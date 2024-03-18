package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.logging.Logger;

public class Sender {

    private static final Logger logger = Logger.getLogger(SampleReceiver.class.getName());
    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection("admin", "admin");

        Session sesion = connection.createSession(/*trans*/false,/*ACK*/Session.AUTO_ACKNOWLEDGE);
        // Should be happened
        connection.start();
        System.out.println("Sender...");


        Destination destination = sesion.createQueue("dynamicQueues/chiendaptrai");

        MessageProducer producer = sesion.createProducer(destination);

        for (int i = 0; i < 10; i++) {
            TextMessage tMsg = sesion.createTextMessage("TextMessage:"+ i);
            producer.send(tMsg);
        }
        System.out.println("Guiii Xonggg");
    }

}
