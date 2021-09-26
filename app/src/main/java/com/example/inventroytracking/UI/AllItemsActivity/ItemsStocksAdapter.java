package com.example.inventroytracking.UI.AllItemsActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventroytracking.Database.entities.StockItem;
import com.example.inventroytracking.R;

import java.util.List;


public class ItemsStocksAdapter extends RecyclerView.Adapter<ItemsStocksAdapter.AssetsViewHolder> {
    private List<StockItem> stocklList ;

    public  ItemsStocksAdapter(List<StockItem> stocklList) {
        this.stocklList = stocklList;
    }

    @NonNull
    @Override
    public AssetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssetsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.stock_item , parent ,false ));
    }

    @Override
    public void onBindViewHolder(@NonNull AssetsViewHolder holder, int position) {
        holder.setAssetItem(stocklList.get(position));

    }

    @Override
    public int getItemCount() {
        return stocklList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static  class AssetsViewHolder extends RecyclerView.ViewHolder {
        TextView barcodeTxt ,  desTxt , locationTxt , statusTxt ;

        AssetsViewHolder(@NonNull View itemView) {
            super(itemView);
            barcodeTxt = itemView.findViewById(R.id.itemBarcode);
            desTxt = itemView.findViewById(R.id.itemDes);
            locationTxt = itemView.findViewById(R.id.itemLoc);
            statusTxt = itemView.findViewById(R.id.itemStatus);
        }

        void setAssetItem(StockItem stockItem){
            barcodeTxt.setText(stockItem.getBarcode());
            desTxt.setText(stockItem.getDescription());
            locationTxt.setText(stockItem.getLocation());
            statusTxt.setText(stockItem.getLocation());
        }
    }
}
