package com.example.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransDAO {
    @Insert
    public void insertTrans(Transactions transactions);

    @Query("SELECT * FROM trans_table ORDER BY trans_id")
    List<Transactions> getAllTransById();
}

