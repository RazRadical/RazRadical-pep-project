package DAO;

import java.util.*;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;

public class MessageDAO {

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                message.setMessage_id(rs.getInt(1));
            }

            return message;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                                             rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return messages;
    }

    public Message getMessageById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, message_id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                                        rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Message> getMessagesByPoster(int posted_by) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, posted_by);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                                             rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return messages;
    }

    public void updateMessageText(int message_id, String message_text) {
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, message_text);
            ps.setInt(2, message_id);

            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteMessageById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, message_id);
        
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}