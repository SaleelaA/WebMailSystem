/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

// divya code starts here
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String id;
    private String password;
    EmailAccount theLoginAccount;

    //get methods and set methods
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public EmailAccount getTheLoginAccount() {
        return theLoginAccount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            return ("internalError");
        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/chinthabathis58";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                    "chinthabathis58", "1560843");
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from emailaccount1 "
                    + "where id = '" + id + "'");

            if (resultSet.next()) {
                //the id is found, check the password
                if (password.equals(resultSet.getString(2))) {
                    //password is good
                    theLoginAccount = new EmailAccount(id, password, resultSet.getString(3));
                    //go to the welcome page 
                    return "welcome";
                } else {
                    //password is not correct
                    id = "";
                    password = "";
                    return ("loginNotOK");
                }
            } else {
                id = "";
                password = "";
                return ("loginNotOK");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ("internalError");
        } finally {
            //close the database
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    
    
    
}
// divya code ends here
