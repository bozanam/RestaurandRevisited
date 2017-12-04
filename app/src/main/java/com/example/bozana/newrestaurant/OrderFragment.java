package com.example.bozana.newrestaurant;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends DialogFragment {

    RestoDatabase db;
    Adapter adapter;
    Cursor cursor;
    ListView orderLV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);


    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        db= RestoDatabase.getInstance(getContext());
        ListView orderLV = getView().findViewById(R.id.orderLV);

        cursor = db.selectAll();
        Cursor data = db.selectAll();
        adapter = new RestoAdapter(getContext(), data);
        orderLV.setAdapter((ListAdapter) adapter);


    }

}
