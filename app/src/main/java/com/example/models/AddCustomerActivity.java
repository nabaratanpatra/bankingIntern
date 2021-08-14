package com.example.models;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomerActivity extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextBalance;
    public static final String EXTRA_POSITION = "com.example.models.EXTRA_POSITION";
    public static final String EXTRA_NAME = "com.example.models.EXTRA_NAME";
    public static final String EXTRA_EMAIL = "com.example.models.EXTRA_EMAIL";
    public static final String EXTRA_BALANCE = "com.example.models.EXTRA_BALANCE";
    public static final String EXTRA_ID = "com.example.models.EXTRA_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextBalance = findViewById(R.id.editTextBalance);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add customer");
    }

    private void saveCust() {
        String cust_name = editTextName.getText().toString();
        String cust_email = editTextEmail.getText().toString();
        String bal = editTextBalance.getText().toString();

        if(cust_name.trim().isEmpty() || bal.isEmpty() || cust_email.isEmpty()){
            Toast.makeText(this, "anything cant be null!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            int cust_balance = Integer.parseInt(bal);
            Intent data = new Intent();
            data.putExtra(EXTRA_NAME, cust_name);
            data.putExtra(EXTRA_EMAIL, cust_email);
            data.putExtra(EXTRA_BALANCE, cust_balance);

            setResult(RESULT_OK, data);     //if everything went through successfully
            finish();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_cust_menu, menu);       //uses add_cust_menu instead of menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.saveCustomerBtn):
                saveCust();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
