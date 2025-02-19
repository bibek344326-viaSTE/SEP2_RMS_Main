package client.networking.chat;

import client.networking.GetServer;
import shared.networking.clientInterfaces.ChatCallBack;
import shared.networking.serverInterfaces.Server;
import shared.utils.Observer;
import shared.utils.Request;
import shared.utils.chat.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChatClientManager implements ChatClient, ChatCallBack {

    private Server server;
    private String userName;
    private String userType;
    private PropertyChangeSupport support;

    public ChatClientManager() {
        this.support = new PropertyChangeSupport(this);
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.server = GetServer.getServerFromRmi();
    }

    @Override
    public void sendMessage(Message message) {
        try {
            server.getChatServer().sendMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Request getAllStaffMembers() throws Exception {
        try {
            return server.getChatServer().getAllStaffMembers();
        } catch (RemoteException e) {
            return new Request("Cannot connect to server", null);
        }
    }

    @Override
    public Request getAllCustomersWhoWantsToChat(String username) {
        try {
            return server.getChatServer().getAllCustomersWhoWantsToChat(username);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new Request("Cannot connect to server", null);
        }
    }

    @Override
    public void setUsername(String username) {
        System.out.println("ChatClient ......username set as" + username);
        this.userName = username;
    }

    @Override
    public String getUsername() throws RemoteException {
        return userName;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getUserType() throws RemoteException {
        return userType;
    }

    @Override
    public void messageReceived(Message message) throws RemoteException {
        System.out.println("Property change fireddddd");
        support.firePropertyChange(Observer.MESSAGE_RECEIVED.toString(), null, message);

    }

    @Override
    public List<Message> getALlMessages(String username, String client) {
        try {
            return server.getChatServer().getAllMessages(username, client);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);

    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);

    }
}
