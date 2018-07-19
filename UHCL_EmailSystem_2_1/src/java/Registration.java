/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.inject.Named;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// saleela code starts here
@ManagedBean
@RequestScoped
public class Registration {

    private String username;
    private String password;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String register() {
        //load the driver
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            return ("Internal Error! Please try again later.");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/chinthabathis58";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, "chinthabathis58", "1560843");
            //crate the statement
            statement = connection.createStatement();

            //do a query
            resultSet = statement.executeQuery("Select * from EmailAccount " + "where id = '" + username + "'");
            //String num=".*[0-9].*";
            // String alpha=".*[A-Za-z].*";
            //if(username.matches(num)&&username.matches(alpha))
            if (resultSet.next()) {
                //if the id is used
                return ("Account creation failed! Either you have an ID already or your ID  is not available to register");
            } else {
                //insert a record into onlineAccount
                int r = statement.executeUpdate("insert into EmailAccount values"
                        + "('" + username + "', '"
                        + password + "','" + name + "')");
                return ("Registration Successful! Please "
                        + "return to login your account.");

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");
        } finally {
            //close the database
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
// sallele  code ends here

