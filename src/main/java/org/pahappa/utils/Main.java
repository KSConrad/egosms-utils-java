package org.pahappa.utils;

import org.pahappa.utils.egosms.EgoSmsClient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger("Main");

    public static void main(String[] args) {
        EgoSmsClient egoSmsClient = EgoSmsClient.builder().username("EgoTest").password("xxxxxx").senderId("EGO SMS").build();

        try {
            //returns a boolean indicating whether the message was sent or not
            boolean sent = egoSmsClient.sendSms("2567xxxxxxxx", "Hello World");

            if (sent) {
                logger.log(Level.INFO, "Message sent successfully");
            } else {
                logger.log(Level.SEVERE, "Message not sent");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}