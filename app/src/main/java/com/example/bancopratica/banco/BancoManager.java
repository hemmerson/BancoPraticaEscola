package com.example.bancopratica.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoManager {
    
    private final BancoHelper dbHelper;

    public BancoManager(Context context){
        dbHelper = new BancoHelper(context);
    }
    
    public SQLiteDatabase open(){
        return dbHelper.getWritableDatabase();
    }
    
    public void close(){
        dbHelper.close();
    }
    
}
