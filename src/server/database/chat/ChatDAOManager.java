package server.database.chat;

import server.database.DatabaseConnection;
import shared.utils.Request;
import shared.utils.user.Usertype;
import shared.utils.chat.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatDAOManager implements ChatDAO{

    private static ChatDAO instance;
    private static Lock lock = new ReentrantLock();

    public ChatDAOManager() throws Exception {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static ChatDAO getInstance() throws Exception {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ChatDAOManager();
                }
            }
        }
        return instance;
    }

    @Override
    public Request addMessage(Message message) throws Exception {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO message 'username_sender', 'username_receiver\",\"Message_Body\") VALUES (?,?,?);");
            statement.setString(1, message.getUserNameSender());
            statement.setString(2, message.getUserNameReceiver());
            statement.setString(3, message.getMessageBody());
            statement.executeUpdate();
            return new Request("Message sent", message);
        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }
    }

    @Override
    public Request getStaffMember() throws Exception {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM user WHERE 'accestype'=?;");
            statement.setString(1, Usertype.STAFFMEMBERS.toString());
            ResultSet resultSet = statement.executeQuery();
            List<String> staffMembers = new ArrayList<>();
            while (resultSet.next()) {
                staffMembers.add(resultSet.getString("username"));
            }
            return new Request("All Staff Members: ", staffMembers);
        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }
    }

    @Override
    public Request getAllCustomersWhoWantsToChat(String username) throws Exception {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT \"Username_Sender\" from \"Message\" WHERE \"Username_Receiver\" =?;");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            List<String> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(resultSet.getString("Username_Sender"));
            }
            return new Request("Customers wanting to chat", customers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new Exception(throwables.getMessage());
        }
    }

    @Override
    public List<Message> getAllMessages(String username, String client) throws Exception {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"Message\" WHERE \"Username_Sender\"=? AND \"Username_Receiver\"=? OR \"Username_Sender\"=? AND \"Username_Receiver\"=?; ");
            statement.setString(1, username);
            statement.setString(2, client);
            statement.setString(3, client);
            statement.setString(4, username);
            ResultSet resultSet = statement.executeQuery();
            List<Message> allMessages = new ArrayList<>();
            while (resultSet.next()) {
                String usernameSender = resultSet.getString("Username_Sender");
                String usernameReceiver = resultSet.getString("Username_Receiver");
                String messageBody = resultSet.getString("Message_Body");
                Message message = new Message(usernameSender, usernameReceiver, messageBody);
                allMessages.add(message);
            }
            return allMessages;
        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }
    }
}
