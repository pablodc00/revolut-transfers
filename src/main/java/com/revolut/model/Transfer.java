package com.revolut.model;

import java.util.Date;

public class Transfer extends EntityBase {

    private String senderId;
    private String receiverId;
    private Double amount;
    private Date date;    
    
    
    public Transfer(String senderId, String receiverId, Double amount, Date date) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.date = date;
    }
    

    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiver(String receiverId) {
        this.receiverId = receiverId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
