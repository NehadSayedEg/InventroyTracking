package com.example.inventroytracking.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.inventroytracking.Database.StockDatabase;
import com.example.inventroytracking.Database.entities.StockItem;
import com.example.inventroytracking.R;
import com.example.inventroytracking.UI.AllItemsActivity.AllItemsActivity;
import com.example.inventroytracking.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    public static final String TAG = HomeActivity.class.getSimpleName();
    private List<StockItem> stockList = new ArrayList<>();


    private List<String> LocationlList = new ArrayList<>();
    String location = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            stockList = StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems();
            Log.i("DatabaseSize",
                    stockList.get(0).getLocation()
                            + "");

            for (int i = 0 ; i< stockList.size() ; i++ ) {
                String loc = stockList.get(i).getLocation();
                LocationlList.add(loc);

                Log.i("loc ", loc + "");
            }



            //Background work here
            handler.post(() -> {
                //UI Thread work here

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter( this , R.layout.option_item_location ,LocationlList);

                binding.autoComplete.setAdapter(arrayAdapter);
                binding.autoComplete.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent , View view , int position , long id){
                        String item  = parent.getItemAtPosition(position).toString();
                        location = item ;
                        Toast.makeText(getApplicationContext() , "Item :" + location , Toast.LENGTH_LONG).show();

                    }


                });
            });
        });


        binding.AllAssetsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , AllItemsActivity.class);
                startActivity(intent);


            }
        });

        binding.scanLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location != null){
                    Intent intent = new Intent(HomeActivity.this , ScanLocationActivity.class);
                    intent.putExtra("loc_key",location);
                    startActivity(intent);
                }else{

                    Toast.makeText(getApplicationContext() , "Please select  location first  :" , Toast.LENGTH_LONG).show();

                }


            }
        });





        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearAssetsDB();
                Toast.makeText(getApplicationContext() , "Bach to Main" , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this , MainActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }



    private void clearAssetsDB() {

        new Thread(() -> {
            Log.i(TAG, "doInBackground: Clear database Table ...");

            StockDatabase.getStockDatabase(getApplicationContext()).stockDao().DeleteAllItems();

            Log.i("DatabaseSize",
                    StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems().size()+ "");

        }).start();

    }


    private void getAssetsLocation() {

        new Thread(() -> {
            Log.i(TAG, "doInBackground: get location database Table ...");
            stockList = StockDatabase.getStockDatabase(getApplicationContext()).stockDao().getAllItems();

//                    for (int i = 0 ; i<= assetModelList.size() ; i++ ) {
//                       String loc = assetModelList.get(i).getLocation();
//
//                        Log.i("loc ", loc + "");
//                    }
        }).start();

    }




}