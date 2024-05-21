/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author PC-Etudiante Info
 */
public class Users {
    private int codeUser;
    private String username;
    private String password;

    public Users(int codeUser, String username, String password) {
        this.codeUser = codeUser;
        this.username = username;
        this.password = password;
    }

    public int getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(int codeUser) {
        this.codeUser = codeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" + "codeUser=" + codeUser + ", username=" + username + ", password=" + password + '}';
    }
    
    
}
