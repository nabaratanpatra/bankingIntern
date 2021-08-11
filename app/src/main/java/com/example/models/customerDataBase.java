package com.example.models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.security.PrivilegedAction;


@Database(entities = customer.class, version = 1, exportSchema = false)       //VERSION DETERMINES SCHEMA VERSION. can ignore as we reinstall app if schema altered
public abstract class customerDataBase extends RoomDatabase {

    private static customerDataBase cust_db_instance;       //we req singleton. we alter this "one" instance all throughout the app

    public abstract customerDAO custDao();      //used to access db operation methods. Room auto gen necessary codes

    public static synchronized customerDataBase getInstance(Context context) {      //create an instance of db. call this method from outside and get a handle to this instance
        if(cust_db_instance == null){
            cust_db_instance = Room.databaseBuilder(context.getApplicationContext(), customerDataBase.class, "cust_dbOinstance")
                    .fallbackToDestructiveMigration()       //if version number increases, room will delete prev database and start from scratch. MUST else crashes
                    .addCallback(roomCallBack)
                    .build();
        }
        return cust_db_instance;
    }
//-----------------------POPULATE DB WITH DUMMY ENTITIES----------------------------------------------------------------------------

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {       //CALLED WHEN THE DATABASE IS CREATED
            super.onCreate(db);
            new populateDbAsync(cust_db_instance).execute();
        }
    };

    private static class populateDbAsync extends AsyncTask<Void, Void, Void>{
        private customerDAO customerDAO;
        populateDbAsync(customerDataBase customerDataBase){     //no member variable of custDao. hence we get it from customerDataBase
            this.customerDAO = customerDataBase.custDao();      //possible because onCreate in called after the dataBase was created
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDAO.insert(new customer("nameA", "nameA@gmail.com", 3400));
            customerDAO.insert(new customer("nameB", "nameB@gmail.com", 1230));
            customerDAO.insert(new customer("nameC", "nameC@gmail.com", 2420));
            customerDAO.insert(new customer("nameD", "nameD@gmail.com", 6450));
            customerDAO.insert(new customer("nameE", "nameE@gmail.com", 4530));
            customerDAO.insert(new customer("nameF", "nameF@gmail.com", 64540));
            customerDAO.insert(new customer("nameG", "nameG@gmail.com", 343));
            customerDAO.insert(new customer("nameH", "nameH@gmail.com", 3120));
            customerDAO.insert(new customer("nameI", "nameI@gmail.com", 40));
            customerDAO.insert(new customer("nameJ", "nameJ@gmail.com", 3340));
            customerDAO.insert(new customer("nameA", "nameA@gmail.com", 3400));
            customerDAO.insert(new customer("nameB", "nameB@gmail.com", 1230));
            customerDAO.insert(new customer("nameC", "nameC@gmail.com", 2420));
            customerDAO.insert(new customer("nameD", "nameD@gmail.com", 6450));
            customerDAO.insert(new customer("nameE", "nameE@gmail.com", 4530));
            customerDAO.insert(new customer("nameF", "nameF@gmail.com", 64540));
            customerDAO.insert(new customer("nameG", "nameG@gmail.com", 343));
            customerDAO.insert(new customer("nameH", "nameH@gmail.com", 3120));
            customerDAO.insert(new customer("nameI", "nameI@gmail.com", 40));
            customerDAO.insert(new customer("nameJ", "nameJ@gmail.com", 3340));



            return null;
        }
    }
}
