package com.example.inventroytracking.UI.AllItemsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.inventroytracking.Database.StockDatabase;
import com.example.inventroytracking.Database.entities.StockItem;
import com.example.inventroytracking.R;
import com.example.inventroytracking.databinding.ActivityAllItemsBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllItemsActivity extends AppCompatActivity {


    private ActivityAllItemsBinding binding;
    public static final String TAG = AllItemsActivity.class.getSimpleName();
    private RecyclerView allAssetsRV ;
    private List<StockItem> stockList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);


        binding = ActivityAllItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(() -> {
            Log.i(TAG, "doInBackground: Importing...");

            stockList =
                    StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems();


        }).start();





        getAllAssets();


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            stockList =    StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems();
            Log.i("DatabaseSize",
                    stockList.get(0).getLocation()
                            + "");




            //Background work here
            handler.post(() -> {
                //UI Thread work here

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);

                binding.allStockRV.setLayoutManager(layoutManager );
                ItemsStocksAdapter adapter = new ItemsStocksAdapter(stockList);
                binding.allStockRV.setAdapter(adapter);


            });
        });

    }


    private void getAllAssets() {

        new Thread(() -> {
            Log.i(TAG, "doInBackground: get All assets from Assets Table ...");


            Log.i("DatabaseSize",
                    StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems().size()
                            + "");
            stockList =
                    StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems();







            Log.i("Database string",
                    stockList.get(0).getDescription()+ "");
//            Log.i("Database string",
//                    assetModelList.get(1).getBarcode()+ "");

        }).start();

    }


}