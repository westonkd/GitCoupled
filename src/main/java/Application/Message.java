/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.sql.Timestamp;

/**
 *
 * @author McKay
 */
public class Message {
    
    private int id;
    private String subject;
    private String body;
    private Timestamp sent_date;
    private int sent_from;
    private int recieved_by;

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public Message(String subject, String body, int sent_from, int recieved_by) {
        this.subject = subject;
        this.body = body;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getSent_date() {
        return sent_date;
    }

    public void setSent_date(Timestamp sent_date) {
        this.sent_date = sent_date;
    }

    public int getSent_from() {
        return sent_from;
    }

    public void setSent_from(int sent_from) {
        this.sent_from = sent_from;
    }

    public int getRecieved_by() {
        return recieved_by;
    }

    public void setRecieved_by(int recieved_by) {
        this.recieved_by = recieved_by;
    }
    
}
