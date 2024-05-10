package client.model.chat;

import shared.utils.Request;
import shared.utils.chat.Message;

import java.util.List;

public interface ChatModel {
    void sendMessage(Message message);
    Request getAllStaffMembers();
    Request getAllCustomersWhoWantsToChat(String username);
    List<Message> getAllMessages(String username, String client);

}
