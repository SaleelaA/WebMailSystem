/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author Chint
 */
public class Email {
      private int id;
     // private String name;
      private String Subject;
      private String content;
      private  String fromid;
      private String toid;

   
      private String date;
      private String type;
      private String status;
      private int parentid;
      private String notistatus;
    



public Email( int i,String sub, String con,String from, String to,String dt,String ty,String st,int pid,String nt)
{
  id=i;
 // name=na;
  Subject=sub;
  content=con;
  fromid=from;
  toid=to;
  date=dt;
  type=ty;
  status=st;
  parentid=pid;
  notistatus=nt;
  
  



}

   // public String getName() {
   //     return name;
   // }

   // public void setName(String name) {
   //     this.name = name;
   // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getNotistatus() {
        return notistatus;
    }

    public void setNotistatus(String notistatus) {
        this.notistatus = notistatus;
    }
    
     public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }
}
