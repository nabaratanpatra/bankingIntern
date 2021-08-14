package com.example.models.TransactionsPack;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransDAO {
    @Insert
    public void insertTrans(Transactions transactions);

    @Query("SELECT * FROM trans_table ORDER BY trans_id")
    List<Transactions> getAllTransById();
}

