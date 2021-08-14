package com.example.models.TransactionsPack;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Transactions.class, version = 1, exportSchema = false)
public abstract class TransDataBase extends RoomDatabase {

    private static TransDataBase transDataBaseInstance;

    public abstract TransDAO transDAO();

    public static synchronized TransDataBase getInstance(Context context) {
        if (transDataBaseInstance == null) {
            transDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(), TransDataBase.class, "trans_dbInstance")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack1)
                    .build();
        }
        return transDataBaseInstance;
    }



//    ----------------------------------

    private static RoomDatabase.Callback roomCallBack1 = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {       //CALLED WHEN THE DATABASE IS CREATED
            super.onCreate(db);
            new populateDbAsync1(transDataBaseInstance).execute();
        }
    };

    private static class populateDbAsync1 extends AsyncTask<Void, Void, Void> {
        private TransDAO transDAO;
        populateDbAsync1(TransDataBase transDataBase){     //no member variable of custDao. hence we get it from customerDataBase
            this.transDAO = transDataBase.transDAO();      //possible because onCreate in called after the dataBase was created
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transDAO.insertTrans(new Transactions("dummySender","dummyRec",420));
            return null;
        }
    }
}
