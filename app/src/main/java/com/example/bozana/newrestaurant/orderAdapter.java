package com.example.bozana.newrestaurant;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;

/**
 * Created by Bozana on 04/12/2017.
 */

public class orderAdapter extends ResourceCursorAdapter {
    public orderAdapter(Context context, Cursor cursor) {
        super(context, R.layout.rowlayout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String item = cursor.getString(1);
        int prijs = cursor.getInt(2);
    }
}
