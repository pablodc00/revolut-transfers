package com.revolut.model;

import org.apache.commons.lang3.Validate;

public class User extends EntityBase {

    private String lastName;
    private String firstName;
    private Double balance;

    public User(String lastName, String firstName, Double balance) {
        Validate.notEmpty(lastName, "Last name is empty.");
        Validate.notEmpty(firstName, "First name is empty.");
        Validate.notNull(balance, "Balance is null.");        

        this.lastName = lastName;
        this.firstName = firstName;
        this.balance = balance;
    }
    
   
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
   
}