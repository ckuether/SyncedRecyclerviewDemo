package com.example.corey.syncedrecyclerviewdemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    Context mContext;
    ArrayList<Integer> rvItems;
    boolean isTop;

    public RVAdapter(Context context, ArrayList<Integer> items, boolean top){
        mContext = context;
        rvItems = items;
        isTop = top;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, final int pos) {
        holder.itemNumber.setText(Integer.toString(rvItems.get(pos)));
        holder.rvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTop)
                    Toast.makeText(mContext, "Top Item: " + Integer.toString(rvItems.get(pos)), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Bottom Item: " + Integer.toString(rvItems.get(pos)), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvItems.size();
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder{

        LinearLayout rvItem;
        TextView itemNumber;

        public RVViewHolder(View view){
            super(view);
            rvItem = (LinearLayout) view.findViewById(R.id.rv_item);
            itemNumber = (TextView) view.findViewById(R.id.item_number);
        }
    }
}
