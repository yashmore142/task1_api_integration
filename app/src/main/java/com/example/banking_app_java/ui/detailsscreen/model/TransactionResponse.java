package com.example.banking_app_java.ui.detailsscreen.model;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse {
    @SerializedName("id")
    int id;

    @SerializedName("date")
    String date;

    @SerializedName("amount")
    int amount;

    @SerializedName("category")
    String category;

    @SerializedName("description")
    String description;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
