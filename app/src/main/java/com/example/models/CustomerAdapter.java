package com.example.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> {
    private List<customer> customers = new ArrayList<>();       //must initialize, else it can be null
    private onItemClickedListener listener;


    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        return new CustomerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHolder holder, int position) {
        customer curCustomer = customers.get(position);
        holder.textViewId.setText(String.valueOf(curCustomer.getCust_id()));
        holder.textViewName.setText(curCustomer.getName());
        holder.textViewMail.setText(curCustomer.getEmail());
        holder.textViewBal.setText(String.valueOf(curCustomer.getBalance()));
    }

    @Override
    public int getItemCount() {     //how many items to display in RecyclerView. that is maximum possible
        return customers.size();
    }

    public void setCustomers(List<customer> customers) {     //adds data to the empty initialized List
        this.customers = customers;
        notifyDataSetChanged();
    }

    public customer getCustAt(int position) {
        return customers.get(position);
    }


   class CustomerHolder extends RecyclerView.ViewHolder {
        private TextView textViewId, textViewName, textViewBal, textViewMail;

        public CustomerHolder(@NonNull final View itemView) {     //the itemView that got passed is the card itself
            super(itemView);
            textViewId = itemView.findViewById(R.id.cust_id);
            textViewName = itemView.findViewById(R.id.cust_name);
            textViewMail = itemView.findViewById(R.id.cust_mail);
            textViewBal = itemView.findViewById(R.id.cust_bal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {     //invalid position when we delete an item but it is still in its animation
                        listener.onItemClicked(customers.get(position), position);
                    }
                }
            });
        }
    }

    public interface onItemClickedListener {
        void onItemClicked(customer customer, int pos);
    }

    public void setOnItemClickListener(onItemClickedListener listener) {
        this.listener = listener;
    }
}
