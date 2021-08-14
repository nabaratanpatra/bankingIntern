package com.example.models;

import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

@Entity(tableName = "trans_table")
public class Transactions  {

    @PrimaryKey(autoGenerate = true)
    private int trans_id;

    private String c1;

    private String c2;

    private int amount;

    Transactions(String c1, String c2, int amount) {
        this.c1 = c1;
        this.c2 = c2;
        this.amount = amount;
    }

//    public customer getSender() {
//        return c1;
//    }
//
//    public customer getReceiver() {
//        return c2;
//    }

    public void setTrans_id(int id) {
        this.trans_id = id;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public int getAmount() {
        return amount;
    }

    public String getC1(){
        return this.c1;
    }

    public String getC2(){
        return this.c2;
    }
}