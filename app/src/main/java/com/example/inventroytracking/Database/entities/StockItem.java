package com.example.inventroytracking.Database.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "stock_table")
public class StockItem implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "barcode")
    private String barcode ;


    @ColumnInfo(name = "location")
    private String location ;


    @ColumnInfo(name = "shelf")
    private String shelf ;


    @ColumnInfo(name = "description")
    private String description ;
    @ColumnInfo(name = "qty")
    private float qty ;


    @ColumnInfo(name = "price")
    private float price ;



//    public StockItem(@NonNull String barcode, String location, String description, int qty, int price) {
//        this.barcode = barcode;
//        this.location = location;
//        this.description = description;
//        this.qty = qty;
//        this.price = price;
//    }


    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    @Nullable
    @Override
    public String toString() {
        return "AssetModel{" +
                "barcode='" + barcode + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
