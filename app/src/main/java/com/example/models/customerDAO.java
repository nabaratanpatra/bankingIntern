package com.example.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface customerDAO {

    @Insert
    void insert(customer customer);

    @Update
    void update(customer customer);

    @Delete
    void delete(customer customer);

//    @Query("UPDATE cust_table SET balance = amount WHERE cust_id = id")
//    void updateBal(int id, int amount);


    @Query("DELETE FROM cust_table")
    void deleteAllCustomer();

    @Query("SELECT * FROM CUST_TABLE ORDER BY cust_id")
//    List<customer> getAllNotesById();     //not observable
    LiveData<List<customer>> getAllCustById(); //observable data. changes realtime
}
