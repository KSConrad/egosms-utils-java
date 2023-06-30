package org.pahappa.utils.egosms.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the JSON request body required to send a message using EgoSMS
 * see <a href="https://developers.pahappa.com/usage/json-sms-api/">Ego SMS Docs</a>
 *
 * @version 1.0
 *
 * @author katusiimeconrad
 */
@Getter
@Setter
@Builder
public class SmsRequest {
    private String method;
    private UserData userdata;
    private MessageData[] msgdata; //this is deliberately misspelled to match the API
}