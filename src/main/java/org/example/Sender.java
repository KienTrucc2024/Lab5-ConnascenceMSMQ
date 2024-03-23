package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.model.Student;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Sender {


    private static final Logger logger = Logger.getLogger(SampleReceiver.class.getName());
    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();



        try (Connection connection = factory.createConnection("admin", "admin");){
            Session sesion = connection.createSession(/*trans*/false,/*ACK*/Session.AUTO_ACKNOWLEDGE);
            // Should be happened
            connection.start();
            System.out.println("Sender...");


            Destination destination = sesion.createQueue(Constants.DYNAMIC_QUEUES_CHIENDAPTRAI);

            MessageProducer producer = sesion.createProducer(destination);
// Sent Message
            for (int i = 0; i < 10; i++) {
                TextMessage tMsg = sesion.createTextMessage("TextMessage:"+ i);
                producer.send(tMsg);
            }
            System.out.println("Guii 10 Text");

//        Sent Object Message
            Student student = new Student(1001l, "NguyenDucChien" );
            ObjectMessage oMsg = sesion.createObjectMessage(student);

            producer.send(oMsg);
            System.out.println("Sent OBJECTTT");
        }
        }
}
