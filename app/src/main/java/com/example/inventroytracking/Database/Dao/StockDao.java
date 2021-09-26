package com.example.inventroytracking.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.inventroytracking.Database.entities.StockItem;

import java.util.List;

@Dao
public interface StockDao {

    @Query("SELECT * FROM  stock_table")
    List<StockItem> getAllItems();




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIem(StockItem stockItems);

    @Query("DELETE  FROM  stock_table")
    public void DeleteAllItems();


}
