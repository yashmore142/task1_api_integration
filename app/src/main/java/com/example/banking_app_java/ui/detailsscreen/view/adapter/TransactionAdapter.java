package com.example.banking_app_java.ui.detailsscreen.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banking_app_java.databinding.TransactionItemBinding;
import com.example.banking_app_java.ui.detailsscreen.model.TransactionResponse;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private ArrayList<TransactionResponse> list;

    public TransactionAdapter(ArrayList<TransactionResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TransactionItemBinding binding = TransactionItemBinding.inflate(inflater, parent, false);
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.binding.txtTitle.setText(list.get(position).getCategory());
        holder.binding.txtDescription.setText(list.get(position).getDescription());
        holder.binding.txtDate.setText(list.get(position).getDate());
        holder.binding.txtAmount.setText(String.valueOf(list.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TransactionItemBinding binding;

        public TransactionViewHolder(TransactionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}

