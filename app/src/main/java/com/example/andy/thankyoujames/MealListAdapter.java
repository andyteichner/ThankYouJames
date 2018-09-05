package com.example.andy.thankyoujames;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Quelle: https://www.youtube.com/watch?v=YMJSBHAZsso&t=611s
public class MealListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Meal> shoppedMealList;

    public MealListAdapter(Context mContext, List<Meal> shoppedMealList) {
        this.mContext = mContext;
        this.shoppedMealList = shoppedMealList;
    }

    @Override
    public int getCount() {
        return shoppedMealList.size();
    }

    @Override
    public Object getItem(int i) {
        return shoppedMealList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= View.inflate(mContext, R.layout.meal_adapter_items, null);
        TextView adapterName = (TextView)v.findViewById(R.id.adapter_name);
        TextView adapterDesc = (TextView)v.findViewById(R.id.adapter_desc);
        TextView adapterPrice = (TextView)v.findViewById(R.id.adapter_price);

        adapterName.setText(shoppedMealList.get(i).getMealName());
        adapterDesc.setText(shoppedMealList.get(i).getDescription());


        adapterPrice.setText(String.valueOf(shoppedMealList.get(i).getPrice())+ "€");

        v.setTag(shoppedMealList.get(i).getMealID());

        return v;
    }
}
