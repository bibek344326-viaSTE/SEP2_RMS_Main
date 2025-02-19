package client.model.chat;

import client.networking.chat.ChatClient;
import shared.utils.Request;
import shared.utils.chat.Message;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatModelManager implements ChatModel{
    //private ChatClient chatClient;
    private PropertyChangeSupport support;

    public ChatModelManager(ChatClient chatClient) {
        support = new PropertyChangeSupport(chatClient);
    }

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public Request getAllStaffMembers() {
        return null;
    }

    @Override
    public Request getAllCustomersWhoWantsToChat(String username) {
        return null;
    }

    @Override
    public List<Message> getAllMessages(String username, String client) {
        return List.of();
    }
}
