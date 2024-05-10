package shared.utils.chat;

import java.io.Serializable;

public class Message implements Serializable {
    private String userNameSender;
    private String userNameReceiver;
    private String messageBody;

    public Message(String userNameSender, String userNameReceiver, String messageBody) {
        this.userNameSender = userNameSender;
        this.userNameReceiver = userNameReceiver;
        this.messageBody = messageBody;
    }

    public String getUserNameSender() {
        return userNameSender;
    }

    public void setUserNameSender(String userNameSender) {
        this.userNameSender = userNameSender;
    }

    public String getUserNameReceiver() {
        return userNameReceiver;
    }

    public void setUserNameReceiver(String userNameReceiver) {
        this.userNameReceiver = userNameReceiver;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String toString() {
        return userNameSender + ": " + userNameReceiver + ": " + messageBody;
    }
}
