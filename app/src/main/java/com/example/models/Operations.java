package com.example.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class Operations extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextBal;
    customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextBal = findViewById((R.id.editTextBal));

        Intent intent = getIntent();
        customer = (customer) intent.getSerializableExtra("OBJECT");

        editTextName.setText(customer.getName());
        editTextEmail.setText(customer.getEmail());
        editTextBal.setText(String.valueOf(customer.getBalance()));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }


    public void deleteCustBtn(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("OBJECTs", customer);
        String viewId =  view.getResources().getResourceName(view.getId());
        i.putExtra("VIEW_ID", viewId);

        setResult(RESULT_OK, i);
        startActivity(i);
        finish();
    }

    public void saveChangesBtn(View view) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String balance = editTextBal.getText().toString();


        if (name.trim().isEmpty() || email.trim().isEmpty() || balance.trim().isEmpty()) {
            Toast.makeText(this, "fillup everything", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, MainActivity.class);

            customer customer1 = new customer(name, email, Integer.parseInt(balance));      //make a new customer from details provided
            customer1.setCust_id(customer.getCust_id());        //set previous id as new customers id

            Toast.makeText(this, "new custId:"+customer1.getCust_id(), Toast.LENGTH_SHORT).show();
            i.putExtra("OBJECTs", customer1);

            String viewId =  view.getResources().getResourceName(view.getId());
            i.putExtra("VIEW_ID", viewId);
            setResult(RESULT_OK, i);

            startActivity(i);
            finish();
        }

    }
}