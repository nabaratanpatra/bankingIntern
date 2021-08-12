package com.example.models;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.models.CustomerAdapter.onItemClickedListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomerViewModel customerViewModel;
    public static final int ADD_CUSTOMER_REQ = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("definetly not scam");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //takes care of displaying the items below each other
        recyclerView.setHasFixedSize(true);

        final CustomerAdapter customerAdapter = new CustomerAdapter();
        recyclerView.setAdapter(customerAdapter);

//        customerViewModel = new CustomerViewModel(getApplication());
        //we donot do this because with every new activity, a new instance of ViewModel will be created
        //the system knows when a new ViewModel is req or use of prev is possible therefore...
        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);   //therefore the system knows to whos life cycle, the viewModel has to be scoped to
        customerViewModel.getAllCustById().observe(this, new Observer<List<customer>>() {
            @Override
            public void onChanged(List<customer> customers) {
                //update recycler View
                customerAdapter.setCustomers(customers);

            }
        });

        customerAdapter.setOnItemClickListener(new onItemClickedListener() {
            @Override
            public void onItemClicked(customer customer, int pos) {
                Intent intent = new Intent(MainActivity.this, Operations.class);
                intent.putExtra(AddCustomerActivity.EXTRA_POSITION, pos);

//                Toast.makeText(MainActivity.this, "pos:" + pos + ", id:" + customer.getCust_id(), Toast.LENGTH_SHORT).show();
                intent.putExtra("OBJECT", customer);
                startActivity(intent);
            }
        });


        //--FOR INTENTS THAT OPENED THIS ACTIVITY
        Intent i = getIntent();

        if (i.hasExtra("OBJECTs") && i.getStringExtra("VIEW_ID").equals("com.example.models:id/deleteCustBtn")) {     //delete. if object and pos is passed in intent

            //DELETE HERE
            customer customer1 = (customer) i.getSerializableExtra("OBJECTs");
            Toast.makeText(this, "delete called on " + customer1.getCust_id(), Toast.LENGTH_SHORT).show();
            customerViewModel.delete(customer1);

        } else if (i.hasExtra("OBJECTs") && i.getStringExtra("VIEW_ID").equals("com.example.models:id/saveCustBtn")) {               //update. if only object is passed in intent

            customer customer1 = (customer) i.getSerializableExtra("OBJECTs");

            if (customer1.getCust_id() == -1) {
                Toast.makeText(this, "cant be updated (default int passed)", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "name: " + customer1.getName() + " id: " + customer1.getCust_id(), Toast.LENGTH_SHORT).show();
                customerViewModel.update(customer1);
                Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();

            }
        } else {
//            Toast.makeText(this, "no intents received", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CUSTOMER_REQ && resultCode == RESULT_OK) {     //if res_code == res_okay
            String cust_name = data.getStringExtra(AddCustomerActivity.EXTRA_NAME);
            String cust_email = data.getStringExtra(AddCustomerActivity.EXTRA_EMAIL);
            int cust_balance = data.getIntExtra(AddCustomerActivity.EXTRA_BALANCE, 0);  //2nd parameter is default value

            customer customer = new customer(cust_name, cust_email, cust_balance);
            customerViewModel.insert(customer);

            Toast.makeText(this, "Customer Added!!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == ADD_CUSTOMER_REQ && resultCode != RESULT_OK) {      //when resCode != res_okay i.e. when we left the add customer activity by pressing back button
            Toast.makeText(this, "nothing done", Toast.LENGTH_SHORT).show();
        }

    }

    public void addCustBtn(View view) {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivityForResult(intent, ADD_CUSTOMER_REQ);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;        //so it will be displayed
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAllBtn:
                customerViewModel.deleteAllCustomer();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "onBackCAlled", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}