/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC-Etudiante Info
 */
public class LaConnexion {
    private static Connection con;
    private static String user;
    private static String password;
    
    public static Connection seConnecter(){
        if(con==null){
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
                System.out.println("connexion établie");
            }catch(SQLException ex){
                System.out.println("bd non trouveé");
            }
        }
        return con;
    }

    public  String getUser() {
        return user;
    }

    public  void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }
    
    
    
}
