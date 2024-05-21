/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author PC-Etudiante Info
 */
public class DAOUser {
    private static Connection con = LaConnexion.seConnecter();
    
    
   public static boolean login(String username, String password) {
    PreparedStatement ps;
    ResultSet rs;
    String query = "select password from users where username = ?";
    //string query =" select * from users where username=? , password = ?";
    try {
        ps = con.prepareStatement(query);
        ps.setString(1, username);
        rs = ps.executeQuery();
        if (rs.next()) {
            String hashedPassword = rs.getString("password");

            return BCrypt.checkpw(password, hashedPassword);
        }
        return false;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
}

   
   public static boolean addUser(String username, String password,String telephone,String adresse) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        PreparedStatement ps;
        String query = "insert into users (username, password,telephone,adresse) values (?, ?, ?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, telephone);
            ps.setString(4, adresse);
           
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("Nom d'utilisateur déjà existant !");
            } else {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }
    
}
