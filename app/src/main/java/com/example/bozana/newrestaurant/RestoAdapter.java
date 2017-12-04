package com.example.bozana.newrestaurant;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Bozana on 04/12/2017.
 */

public class RestoAdapter extends ResourceCursorAdapter{
    public RestoAdapter(Context context, Cursor cursor){
        super(context, R.layout.rowlayout, cursor);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView dish = view.findViewById(R.id.tvDish);
        TextView price = view.findViewById(R.id.tvPrice);
        String item = cursor.getString(1);

        int prijs = cursor.getInt(2);
        String dePrijs = cursor.getString(2);
        dish.setText(item);
        price.setText("€" + dePrijs);

        TextView total = view.findViewById(R.id.total);



    }






}
