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
    private int in_reply_to;
    private int display;
    private String from;
    private String to;

    /**
     * 
     * @param subject
     * @param body 
     */
    public Message(String subject, String body) {
        this.id = -1;
        this.subject = subject;
        this.body = body;
    }

    /**
     * 
     * @param subject
     * @param body
     * @param sent_from
     * @param recieved_by 
     */
    public Message(String subject, String body, int sent_from, int recieved_by) {
        this.id = -1;
        this.subject = subject;
        this.body = body;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
        this.in_reply_to = -1;
    }
    
    /**
     * 
     * @param subject
     * @param body
     * @param sent_from
     * @param recieved_by 
     * @param in_reply_to
     */
    public Message(String subject, String body, int sent_from, int recieved_by, int in_reply_to) {
        this.id = -1;
        this.subject = subject;
        this.body = body;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
        this.in_reply_to = in_reply_to;
    }
    
    /**
     * 
     * @param subject
     * @param body
     * @param sent_from
     * @param recieved_by
     * @param from
     * @param to 
     */
    public Message(String subject, String body, int sent_from, int recieved_by, String from, String to) {
        this.id = -1;
        this.subject = subject;
        this.body = body;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
        this.from = from;
        this.to = to;
    }

    /**
     * 
     * @param id
     * @param subject
     * @param body
     * @param sent_date
     * @param sent_from
     * @param recieved_by 
     */
    public Message(int id, String subject, String body, Timestamp sent_date, int sent_from, int recieved_by, String from, String to) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.sent_date = sent_date;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
        this.from = from;
        this.to = to;
    }

    /**
     * 
     * @param id
     * @param subject
     * @param body
     * @param sent_date
     * @param sent_from
     * @param recieved_by
     * @param in_reply_to
     * @param display
     * @param from
     * @param to 
     */
    public Message(int id, String subject, String body, Timestamp sent_date, int sent_from, int recieved_by, int in_reply_to, int display, String from, String to) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.sent_date = sent_date;
        this.sent_from = sent_from;
        this.recieved_by = recieved_by;
        this.in_reply_to = in_reply_to;
        this.display = display;
        this.from = from;
        this.to = to;
    }

    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * @param subject 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 
     * @return 
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body 
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 
     * @return 
     */
    public Timestamp getSent_date() {
        return sent_date;
    }

    /**
     * 
     * @param sent_date 
     */
    public void setSent_date(Timestamp sent_date) {
        this.sent_date = sent_date;
    }

    /**
     * 
     * @return 
     */
    public int getSent_from() {
        return sent_from;
    }

    /**
     * 
     * @param sent_from 
     */
    public void setSent_from(int sent_from) {
        this.sent_from = sent_from;
    }

    /**
     * 
     * @return 
     */
    public int getRecieved_by() {
        return recieved_by;
    }

    /**
     * 
     * @param recieved_by 
     */
    public void setRecieved_by(int recieved_by) {
        this.recieved_by = recieved_by;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getIn_reply_to() {
        return in_reply_to;
    }

    public void setIn_reply_to(int in_reply_to) {
        this.in_reply_to = in_reply_to;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }
    
}
