package com.example.models.TransactionsPack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class TransViewModel extends AndroidViewModel {
    private TransRepository transRepository;
    private List<Transactions> allTrans;


     public TransViewModel(@NonNull Application application) {
        super(application);

        transRepository = new TransRepository(application);
        allTrans = transRepository.getAllTransById();
    }

    public void insert(Transactions transactions){
        transRepository.insert(transactions);
    }

    public List<Transactions> getAllTrans(){
        return allTrans;
    }

}
