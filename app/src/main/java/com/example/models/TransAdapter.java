package com.example.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.TransHolder> {
    private List<Transactions> transactions = new ArrayList<>();

    @NonNull
    @Override
    public TransHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans, parent, false);
        return new TransHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TransHolder holder, int position) {
        Transactions currTrans = transactions.get(position);
        holder.senderTextView.setText(currTrans.getC1());
        holder.receiverTextView.setText(currTrans.getC2());
        holder.amountTextView.setText(String.valueOf(currTrans.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<Transactions> transactions){
        this.transactions = transactions;
        notifyDataSetChanged();
    }


    class TransHolder extends RecyclerView.ViewHolder{
        private TextView senderTextView;
        private TextView receiverTextView;
        private TextView amountTextView;

        public TransHolder(@NonNull View itemView) {
            super(itemView);

            senderTextView = itemView.findViewById(R.id.senderTextView);
            receiverTextView = itemView.findViewById(R.id.receiverTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);


        }
    }
}
