package com.eric.sti3desafio.database;

import androidx.room.TypeConverter;

import com.eric.sti3desafio.model.Item;
import com.eric.sti3desafio.model.Pagamento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromItemsList(List<Item> items) {
        if (items == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        String json = gson.toJson(items, type);
        return json;
    }

    @TypeConverter
    public List<Item> toItemsList(String items) {
        if (items == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> itemList = gson.fromJson(items, type);
        return itemList;
    }

    //Payment

    @TypeConverter
    public String fromPagamentosList(List<Pagamento> pagamentos) {
        if (pagamentos == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Pagamento>>() {}.getType();
        String json = gson.toJson(pagamentos, type);
        return json;
    }

    @TypeConverter
    public List<Pagamento> toPagamentosList(String pagamentos) {
        if (pagamentos == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Pagamento>>() {}.getType();
        List<Pagamento> pagamentosList = gson.fromJson(pagamentos, type);
        return pagamentosList;
    }

}
