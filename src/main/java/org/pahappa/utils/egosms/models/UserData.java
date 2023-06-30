package org.pahappa.utils.egosms.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the user data required to send a message using EgoSMS
 * see <a href="https://developers.pahappa.com/usage/json-sms-api/">Ego SMS Docs</a>
 *
 * @author katusiimeconrad
 * @version 1.0
 */
@Getter
@Setter
@Builder
public class UserData {
    private String username;
    private String password;
}