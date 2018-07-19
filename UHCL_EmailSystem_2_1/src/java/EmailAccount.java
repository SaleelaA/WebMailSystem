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
import javax.inject.Named;

import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.Scanner;

// divya code starts here 
public class EmailAccount {

    private String id;   //account ID
    private String password;   //saved password
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
private ArrayList<Email> inbox;
    private int emailIDtoView;
    public EmailAccount(String i, String p, String n) {

        id = i;
        password = p;
        name = n;

        inbox = new ArrayList<Email>();
       
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        final String URL = "jdbc:mysql://mis-sql.uhcl.edu/chinthabathis58";
        try {
            conn = DriverManager.getConnection(URL, "chinthabathis58", "1560843");
            stat = conn.createStatement();
            rs = stat.executeQuery("Select * from email1 "
                    + "where ToID = '" +id+ "'");
            
            while(rs.next()) {  
                Email aNew = new Email(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10));
                             inbox.add(aNew);
              
            }                
        }
        catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                conn.close();
                stat.close();
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    
    
  
    
    public String getEmailByID(int i)
    {
         emailIDtoView = i;
         return "viewEmail";         
    
    }
       
     
     public Email readSelectedEmail()
    {
              final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/chinthabathis58";
        

        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet ps=null;
           try{
                 conn = DriverManager.getConnection(DB_URL,"chinthabathis58","1560843");
      
                   st = conn.createStatement();
     // sallela code 
        for(Email e:inbox){
            if(e.getId()==(emailIDtoView)){
            
            
          //  if you have check whether it is new or reply 
                if(e.getStatus().equals("new")){
                        rs=st.executeQuery("select * from email1 where eid='"+e.getId()+"'");
                        //update  the status 
                    if(e.getNotistatus().equals("yes")){
                        String notify="";
                        notify="your email has been viewed by"+e.getToid()+".";
                        String notifyTitle="";
                        notifyTitle="your email notification";
                        
                       // rs=st.executeQuery(id) insert the mail you have send the notification to the sender that email has been sent.
                     ps=st.executeQuery("insert into email1 (Subject ,Content, FromID,ToID,Date,Type,Status,ParentID)values"
                   + "(\"" + notifyTitle + "\",\"" + notify  + "\",\"" + e.getToid() + "\",\""
                   + e.getFromid()+ "\",now(), \"notifn\",\"unread\",\"" + e.getId() + "\")");
                   
                        return e;
                                
                    }
                }
                    
                //  isread status  read  update 
        //   and you will check the noti status whether it is yes or no if yes ypou have send a mail to the sender 
               // f(e.getNotistatus().equals("yes")){
                 //   rs = st.executeQuery("Select * from emailmain where emailid ='"+viewMail.get(i).reply_id+"' ");
                   // return "Re"+rs.getString(3)+"                                                                               "
                            
                          //  ;
                    
              //// }
                
            }
            
        }
           }
            catch(Exception e){
            e.printStackTrace(); 
        }
        finally{
          
            try{
                 st.close();
              rs.close();
              ps.close();
              conn.close();
               
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
           
        return null;
    }
    

    public ArrayList<Email> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Email> inbox) {
        this.inbox = inbox;
    }
      private static  String newMail;

    public String getNewMail() {
        return newMail;
    }

    public void setNewMail(String newMail) {
        this.newMail = newMail;
    }



    
     public  String reply(Email email_object){
     // String successful="";
             final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/chinthabathis58";
         
        

        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet ps=null;
        try{
                   conn = DriverManager.getConnection(DB_URL,"chinthabathis58","1560843");
      
                   st = conn.createStatement();
               //    String id=email_object.getId();
                   String OldMail="";
                  OldMail=email_object.getContent();
                   String email_Head= "Re: "+email_object.getSubject();
                   String replySender=email_object.getFromid();
                   String replyReciever=email_object.getToid();
               String newcontent=newMail + "<br>--------------- </br>"+ email_object.getSubject();
                
                   String isNew="True";
                   System.out.println("your reply to the mail");
               int r  =st.executeUpdate( "insert into email1 (Subject ,Content, FromID,ToID,date,Type,Status,ParentID)values"
                  + "(\"" + email_Head + "\",\"" + newcontent+ "\",\"" + replyReciever+ "\",\""
                    + replySender + "\",now(), \"reply\",\"unread\",\"" + email_object.getId() + "\")");
   
         
            //successful="your reply has been sent ,thank you";
               
            return ("replyconf.xhtml");
        
                 
        }
             catch(Exception e){
            e.printStackTrace(); 
        }
        finally{
          
            try{
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
         return("replyconf.xhtml");
        
 }

    
    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
     

    
}
// divya code ends here
