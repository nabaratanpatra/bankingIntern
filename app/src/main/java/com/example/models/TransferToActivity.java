package com.example.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class TransferToActivity extends AppCompatActivity {
    private CustomerViewModel customerViewModel;
    private TransViewModel transViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to);

        setTitle("Select receiver");

        RecyclerView recyclerView = findViewById(R.id.recycler_view_receiver);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //takes care of displaying the items below each other
        recyclerView.setHasFixedSize(true);

        final CustomerAdapter customerAdapter = new CustomerAdapter();
        recyclerView.setAdapter(customerAdapter);

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        customerViewModel.getAllCustById().observe(this, new Observer<List<customer>>() {
            @Override
            public void onChanged(List<customer> customers) {
                customerAdapter.setCustomers(customers);

            }
        });




        customerAdapter.setOnItemClickListener(new CustomerAdapter.onItemClickedListener() {
            @Override
            public void onItemClicked(customer customer, int pos) {
                Intent i = getIntent();
                customer customer1 = (customer) i.getSerializableExtra("OBJECTs");

                customer customer2 = customer;      //passed variable
                EditText editTextTransferAmount = findViewById(R.id.editTextTransferAmount);
                String strAmt = editTextTransferAmount.getText().toString();
                if(strAmt.isEmpty()){
                    Toast.makeText(TransferToActivity.this, "Please enter transfer amount", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(customer1.getCust_id() == customer2.getCust_id()){
                    Toast.makeText(TransferToActivity.this, "cannot transfer to same bank Account!", Toast.LENGTH_LONG).show();
                    return;
                }

                final int amount  = Integer.parseInt(strAmt);
                if(amount > customer1.getBalance()){
                    Toast.makeText(TransferToActivity.this, "Insufficient balance", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    final customer customer3 = new customer(customer1.getName(), customer1.getEmail(), customer1.getBalance()-amount);
                    customer3.setCust_id(customer1.getCust_id());
                    final customer customer4 = new customer(customer2.getName(), customer2.getEmail(), customer2.getBalance()+amount);
                    customer4.setCust_id(customer2.getCust_id());

                    Toast.makeText(TransferToActivity.this, "3 and 4 "+customer3.getBalance()+" "+customer4.getBalance(), Toast.LENGTH_LONG).show();

                    customerViewModel.update(customer3);
                    customerViewModel.update(customer4);


                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            transViewModel = ViewModelProviders.of(TransferToActivity.this).get(TransViewModel.class);
                            transViewModel.insert(new Transactions(customer3.getName(), customer4.getName(), amount));
                            Log.d("TAGGGGED", "trans inserted");
                        }
                    });
                    t1.start();





                    Intent intent = new Intent(TransferToActivity.this, MainActivity.class);
                    Toast.makeText(TransferToActivity.this, "Transation successful!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });


    }


}
