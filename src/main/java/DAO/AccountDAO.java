package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
                return new Account(id, account.getUsername(), account.getPassword());
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account getAccountByUsernameAndPassword(String username, String password) { 
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
 
        return null;
    }

    public Account getAccountByAccountId(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, account_id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}