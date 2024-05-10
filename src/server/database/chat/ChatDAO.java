package server.database.chat;

import shared.utils.Request;
import shared.utils.chat.Message;

import java.util.List;

public interface ChatDAO {
    Request addMessage(Message message) throws Exception;

    Request getStaffMember() throws Exception;

    Request getAllCustomersWhoWantsToChat(String username) throws Exception;

    List<Message> getAllMessages(String username, String client) throws Exception;
}
