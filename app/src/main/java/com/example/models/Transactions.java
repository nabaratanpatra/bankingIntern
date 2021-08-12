package com.example.models;

import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "cust_trans")
public class Transactions implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int trans_id;

    private customer c1;

    private customer c2;

    private int amount;

    Transactions(customer c1, customer c2, int amount) {
        this.c1 = c1;
        this.c2 = c2;
        this.amount = amount;
    }

    public customer getSender(){
        return c1;
    }

    public customer getReceiver(){
        return c2;
    }

    public int getAmount(){
        return amount;
    }

}