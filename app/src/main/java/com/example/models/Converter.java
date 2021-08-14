package com.example.models;

import androidx.room.TypeConverter;


import com.google.gson.Gson;

public class Converter {
    Gson gson = gson = new Gson();;

    @TypeConverter
    public String customerToStr(customer customer) {
        return  gson.toJson(customer);
    }

    @TypeConverter
    public customer strTocustomer(String str) {
        return gson.fromJson(str, customer.class);
    }

}
