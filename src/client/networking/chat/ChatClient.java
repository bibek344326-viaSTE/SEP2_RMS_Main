package client.networking.chat;

import shared.utils.Request;
import shared.utils.Subject;
import shared.utils.chat.Message;

import java.util.List;

public interface ChatClient extends Subject {
    void sendMessage(Message message);

    Request getAllStaffMembers() throws Exception;

    Request getAllCustomersWhoWantsToChat(String username);

    void setUsername(String username);

    void setUserType(String userType);

    List<Message> getALlMessages(String username, String client);

}
