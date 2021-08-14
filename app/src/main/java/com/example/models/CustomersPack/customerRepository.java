package com.example.models.CustomersPack;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class customerRepository {
    private customerDAO customerDAO;
    private LiveData<List<customer>> allCustomers;

    public customerRepository(Application application){
        customerDataBase dataBase = customerDataBase.getInstance(application);

        customerDAO = dataBase.custDao();       //abstract method. room auto gen code
        allCustomers = customerDAO.getAllCustById();
    }

    //  DAO DOSNT ALLOW ACCESSING FROM REPO IN MAIN THREAD SO WE USE AsyncTask TO DO IN BACKGROUND
    public void  insert(customer customer){
        new insertAsync(customerDAO).execute(customer);
    }

    public void  update(customer customer){
        new updateAsync(customerDAO).execute(customer);
    }

    public void  delete(customer customer){
        new deleteAsync(customerDAO).execute(customer);
    }

    public void deleteAllCustomer(){
        new deleteAllAsync(customerDAO).execute();
    }

    public LiveData<List<customer>> getAllCustById(){
        return allCustomers;
    }


//    ------------------------------------------------------------------------------------------------
    private static class insertAsync extends AsyncTask<customer, Void, Void>{
        customerDAO customerDAO;

        public insertAsync(customerDAO customerDAO){        //because class is static we need constructor to pass DAO object. to further access .insert()
            this.customerDAO = customerDAO;
        }

        @Override
        protected Void doInBackground(customer... customers) {
            customerDAO.insert(customers[0]);
            return null;
        }
    }


    private static class updateAsync extends AsyncTask<customer, Void, Void>{
        customerDAO customerDAO;

        public updateAsync(customerDAO customerDAO){
            this.customerDAO = customerDAO;
        }
        @Override
        protected Void doInBackground(customer... customers) {
            customerDAO.update(customers[0]);
            return null;
        }
    }


    private static class deleteAsync extends AsyncTask<customer, Void, Void>{
        customerDAO customerDAO;

        public deleteAsync(customerDAO customerDAO){
            this.customerDAO = customerDAO;
        }
        @Override
        protected Void doInBackground(customer... customers) {
            customerDAO.delete(customers[0]);
            return null;
        }
    }


    private static class deleteAllAsync extends AsyncTask<Void, Void, Void>{
        customerDAO customerDAO;

        public deleteAllAsync(customerDAO customerDAO){
            this.customerDAO = customerDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            customerDAO.deleteAllCustomer();
            return null;
        }
    }

}
