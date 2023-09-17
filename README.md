# egosms-utils-java
Project Description:
This project is a utility library for sending SMS messages using the Egosms.co API. It provides a simple and convenient way to send SMS messages by encapsulating the API request logic.

Features:
- Send SMS messages using the Egosms.co API
- Configure the sender ID, recipient number, and message content
- Easy integration with Java projects

Dependencies:
- OkHttp: 4.9.1
- Gson: 2.8.9

Usage:
1. Import the library into your Java project.
2. Create an instance of the EgosmsClient class.
3. Set up the necessary configurations, such as the API credentials and endpoint URL.
4. Use the sendSms() method to send SMS messages with the desired parameters.

Example Code:

```java
 
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

```


For more information and detailed usage instructions, please refer to the project documentation.

Contributing:
Contributions are welcome! If you find any bugs or have suggestions for new features, please open an issue or submit a pull request on GitHub.

License:
This project is licensed under the MIT License. See the LICENSE file for more details.
