package com.example.models.TransactionsPack;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class TransRepository {
    private TransDAO transDAO;
    private List<Transactions> allTransactions;

    public TransRepository(Application application) {
        TransDataBase transDataBase = TransDataBase.getInstance(application);
        transDAO = transDataBase.transDAO();        //we cant call abstract method because they dont have body. but in this case Room auto generates the code
        allTransactions = transDAO.getAllTransById();
    }

    public void insert(Transactions transactions) {
        new insertAsync(transDAO).execute(transactions);
    }

    public List<Transactions> getAllTransById() {
        return allTransactions;
    }


//    ----------------------------------async tasks-------------------------

    private static class insertAsync extends AsyncTask<Transactions, Void, Void> {
        TransDAO transDAO;

        public insertAsync (TransDAO transDAO){
            this.transDAO = transDAO;
        }

        @Override
        protected Void doInBackground(Transactions... transactions) {
            transDAO.insertTrans(transactions[0]);
            return null;
        }
    }




}
