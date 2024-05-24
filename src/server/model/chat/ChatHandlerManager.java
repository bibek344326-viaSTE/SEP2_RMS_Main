package server.model.chat;

import server.database.chat.ChatDAO;
import server.database.chat.ChatDAOManager;
import shared.utils.Request;
import shared.utils.chat.Message;

import java.util.List;

public class ChatHandlerManager implements ChatHandler {

    private ChatDAO chatDAO;
    public ChatHandlerManager() throws Exception {
        chatDAO = ChatDAOManager.getInstance();
    }

    @Override
    public Request sendMessage(Message message) {
        try {
            return ChatDAOManager.getInstance().addMessage(message);
        } catch (Exception e) {
            return new Request(e.getMessage(), null);
        }
    }

    @Override
    public Request getAllStaffMembers() {
        try {
            return ChatDAOManager.getInstance().getStaffMember();
        } catch (Exception e) {
            return new Request(e.getMessage(), null);
        }
    }

    @Override
    public Request getAllCustomersWhoWantsToChat(String username) {
        try {
            return ChatDAOManager.getInstance().getAllCustomersWhoWantsToChat(username);
        } catch (Exception e) {
            return new Request(e.getMessage(),null);
        }
    }

    @Override
    public List<Message> getAllMessages(String username, String client) {
        try {
            return ChatDAOManager.getInstance().getAllMessages(username,client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
