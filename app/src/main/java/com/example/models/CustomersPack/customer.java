package com.example.models.CustomersPack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cust_table")
public class customer implements Serializable {

    @PrimaryKey(autoGenerate = true)        //auto incremented for each entity
    private int cust_id;

    private String name;

    private String email;

    private int balance;


    //constructors (except id, it will be auto-generated)
    public customer(String name, String email, int balance){
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }
}

