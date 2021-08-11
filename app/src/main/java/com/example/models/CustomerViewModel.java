package com.example.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {   //androidViewModel is a subclass of ViewModel. and the only diff is that
                                    // AndroidViewModel gets an Application passed in to the constructor
    private customerRepository customerRepository;
    private LiveData<List<customer>> allCustomers;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new customerRepository(application);
        allCustomers = customerRepository.getAllCustById();
    }

    public void insert(customer customer){
        customerRepository.insert(customer);
    }


    public void update(customer customer){
        customerRepository.update(customer);
    }

    public void delete(customer customer){
        customerRepository.delete(customer);
    }

    public void deleteAllCustomer(){
        customerRepository.deleteAllCustomer();
    }

    public LiveData<List<customer>> getAllCustById() {
        return allCustomers;
    }
}
