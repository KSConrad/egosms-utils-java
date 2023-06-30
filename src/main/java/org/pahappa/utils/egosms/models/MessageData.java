package org.pahappa.utils.egosms.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the user data required to send a message using EgoSMS
 * see <a href="https://developers.pahappa.com/usage/json-sms-api/">Ego SMS Docs</a>
 *
 * @version 1.0
 *
 * @author katusiimeconrad
 */
@Getter
@Setter
@Builder
public class MessageData {
    /**
     * The number to send the message to
     */
    private String number;
    /**
     * The message to send
     */
    private String message;

    /**
     * The sender id to use
     */
    private String senderid; //this is deliberately misspelled to match the API
}