package org.pahappa.utils.egosms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.pahappa.utils.egosms.models.MessageData;
import org.pahappa.utils.egosms.models.SmsRequest;
import org.pahappa.utils.egosms.models.UserData;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a utility class that demonstrates how to send a message using the EgoSMS API
 * <p>
 * see  <a href="https://developers.pahappa.com/usage/json-sms-api/">EgoSms API Docs</a>
 *
 * @author katusiimeconrad
 * @version 1.0
 * @apiNote require Java 8+
 */
public class EgoSmsClient {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");
    private static final Logger logger = Logger.getLogger("EgoSmsClient");
    private static final String METHOD = "SendSms"; //
    private String username;
    private String password;
    private String senderId = "EGO SMS";
    private String url = "https://www.egosms.co/api/v1/json/";


    private EgoSmsClient() {
    }


    /**
     * Creates an instance of the EgoSmsClient with the required parameters.
     * Assumes the default url of the EgoSMS API  <a href="https://www.egosms.co/api/v1/json/">JSON API</a>
     *
     * @param username - the username of the EgoSMS account
     * @param password - the password of the EgoSMS account
     */
    public EgoSmsClient(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Creates an instance of the EgoSmsClient with the required parameters.
     * <p>
     * Assumes the default url of the EgoSMS API  <a href="https://www.egosms.co/api/v1/json/">JSON API</a>
     *
     * @param username - the username of the EgoSMS account
     * @param password - the password of the EgoSMS account
     * @param senderId - the senderId of the EgoSMS account
     */
    public EgoSmsClient(String username, String password, String senderId) {
        this.username = username;
        this.password = password;
        this.senderId = senderId;
        this.url = "https://www.egosms.co/api/v1/json/";
    }

    /**
     * Creates an instance of the EgoSmsClient with the required parameters.
     *
     * @param username - the username of the EgoSMS account
     * @param password - the password of the EgoSMS account
     * @param url      - the url of the EgoSMS API
     * @param senderId - the senderId of the EgoSMS account
     */
    public EgoSmsClient(String username, String password, String url, String senderId) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.senderId = senderId;

    }

    /**
     * Sends a message to a single recipient
     *
     * @param message - the message to be sent
     * @param number  - the number to send the message to
     * @return true if the message was sent successfully, else false
     * @throws IOException - if the request fails
     */
    public boolean sendSms(String message, String number) throws IOException {
        boolean isSuccessful = false;

        OkHttpClient client = new OkHttpClient();

        RequestBody body = createRequestBody(message, number); //create the request body

        Request request = new Request.Builder()
                .url(this.url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if (Objects.nonNull(response.body())) {
                    String responseBody = response.body().string();
                    logger.log(Level.INFO, responseBody);

                    if (responseBody.contains("OK")) {

                        isSuccessful = true;
                        logger.log(Level.INFO, "Message sent successfully");

                    } else {
                        logger.log(Level.INFO, "Message not sent");
                    }
                }

            } else {
                logger.log(Level.SEVERE, "Request failed");
            }
        }

        return isSuccessful;
    }

    /**
     * Constructs the request body for the EgoSMS API for a single SMS
     * <p>
     * For details see <a href="https://developers.pahappa.com/usage/json-sms-api/">EgoSms Docs : Sending Single SMS</a>
     *
     * @param message - the message to send
     * @param number  - the number to send the message to
     * @return - the request body
     */
    private RequestBody createRequestBody(String message, String number) {
        SmsRequest smsRequest = SmsRequest.builder()
                .method(METHOD)
                .userdata(UserData.builder()
                        .username(this.username) //username of the EgoSMS account
                        .password(this.password) //password of the EgoSMS account
                        .build())
                .msgdata(new MessageData[]{
                        MessageData.builder()
                                .senderid(this.senderId) //senderId of the EgoSMS account
                                .number(number) //the number to send the message to
                                .message(message) // the message to send
                                .build()
                })
                .build();


        Gson gson = new GsonBuilder().create();
        String requestBody = gson.toJson(smsRequest);

        return RequestBody.create(requestBody, JSON_MEDIA_TYPE);
    }
}
