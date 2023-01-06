package com.eric.sti3desafio.database;

import android.content.Context;

import androidx.room.Room;

public class DbInstance {
    public MyDatabase getDbInstance(Context context){
        return Room.databaseBuilder(context, MyDatabase.class, "MyDB")
                .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
    }
}
