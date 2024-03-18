package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SampleReceiver {
    private static final Logger logger = Logger.getLogger(SampleReceiver.class.getName());
    public static void main(String[] args) throws Exception{
        System.out.println("Waitingg...");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection("admin", "admin");

        // Should be happened
        connection.start();


        Session sesion = connection.createSession(/*trans*/false,/*ACK*/Session.AUTO_ACKNOWLEDGE);

        Destination destination = sesion.createQueue("dynamicQueues/chiendaptrai");
        MessageConsumer consumer = sesion.createConsumer(destination);

        consumer.setMessageListener(message -> {
            try {
                if(message instanceof TextMessage){
                    String msg = ((TextMessage) message).getText();
                    System.out.println(msg);
                }else if(message instanceof  ObjectMessage){

                } else {
                    System.out.println("NOT KNOW MESSAGE!!!");
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
                }
                );
    }


}
