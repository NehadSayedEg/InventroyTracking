package com.example.inventroytracking.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.inventroytracking.Database.Dao.StockDao;
import com.example.inventroytracking.Database.entities.StockItem;

@Database(entities = {  StockItem.class   }, version = 2 , exportSchema = false)


public  abstract  class StockDatabase extends RoomDatabase {
    public static StockDatabase stockDatabase ;
    public static synchronized StockDatabase getStockDatabase(Context context){
        if(stockDatabase == null){
            stockDatabase = Room.databaseBuilder(context , StockDatabase.class , "stock_db").build();
        }
        return stockDatabase;
    }

    public abstract StockDao stockDao();
}
