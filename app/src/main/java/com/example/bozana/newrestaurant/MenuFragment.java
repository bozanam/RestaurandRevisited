package com.example.bozana.newrestaurant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends ListFragment {

    RestoDatabase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = this.getArguments();
        String choice = arguments.toString();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://resto.mprog.nl/menu?category=";
        db = RestoDatabase.getInstance(getContext());

        if(choice.contains("entrees")){
            url = "https://resto.mprog.nl/menu?category=entrees";
        } else if(choice.contains("appetizers")){
            url = "https://resto.mprog.nl/menu?category=appetizers";
        }

        JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.GET, url ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<String> newArray = new ArrayList<>();
                        JSONArray larray = new JSONArray();

                        try {
                            larray = response.getJSONArray("items");
                            for (int i = 0; i < larray.length(); i++) {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < larray.length(); i++) {
                            try {
                                newArray.add(larray.getJSONObject(i).getString("name") +  "      €" + larray.getJSONObject(i).getString("price") );

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        setAdapter(newArray);

                    }
                }

                , null);
        queue.add(jsonobject);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setAdapter(ArrayList<String> arry){
        ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arry);

        this.setListAdapter(myAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Object positie = l.getItemAtPosition(position);

        String order = positie.toString();
        String[] splitter_order = order.split("€");
        String naam_order = splitter_order[0].toString();
        int prijs =Integer.parseInt(splitter_order[1].replaceAll("[\\D]", ""));
        prijs = (int) (prijs * 0.1);

        Log.d("NAAM", "onListItemClick: " + naam_order);
        Log.d("PRIJS", "onListItemClick: " + prijs);
        db.insert(naam_order, prijs);

    }
}

