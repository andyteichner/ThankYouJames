package com.example.andy.thankyoujames.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andy.thankyoujames.R;
import com.example.andy.thankyoujames.objects.CartItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends ArrayAdapter<CartItem> {

    private TextView cartItemID, cartItemQuantity, cartItemPrice;

    private ArrayList<CartItem> cartItemsList = new ArrayList<CartItem>();

    private final Context context;

    public CartItemAdapter(Context context){
        this.context = context;
    }

    public void update(ArrayList<CartItem> cartItemsList) {
        this.cartItemsList = cartItemsList;
        notifyDataSetChanged();
    }


    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v == null){
            //folgende Zeile überprüfen, wieso wird Layout f. adapter cart item nicht angenommen??
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_cart_item, null);
        }

        CartItem cartItem = cartItemsList.get(position);

        if(v != null){
            cartItemID = (TextView) findViewByID (R.id.textCartItemName);
            cartItemPrice = (TextView) findViewByID (R.id.textCartItemPrice);
            cartItemQuantity = (TextView) findViewByID(R.id.textCartItemQuantity);

            //evtl. noch zu ändern
            cartItemID.setText(cartItem.getFinalFoodID());
            //cartItemPrice.setText(cartItem.getPrice());
            cartItemQuantity.setText(cartItem.getQuantity());

        }

        return v;
    }

    @Override
    public CartItem getItem (int id){

        return cartItemsList.get(id);
    }

    @Override
    public int getCount(){
        return cartItemsList.size();
    }


}
