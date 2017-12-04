package com.example.bozana.newrestaurant;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RestoDatabase db;
    ArrayList<String> myArray = new ArrayList<>() ;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "https://resto.mprog.nl/order";
        db = RestoDatabase.getInstance(getApplicationContext());
        queue = Volley.newRequestQueue(this);
        FragmentManager fm = getSupportFragmentManager();
        Categories_Fragment fragment = new Categories_Fragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment, "categories");
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actions, menu);
            return super.onCreateOptionsMenu(menu);
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.MenuItem:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    OrderFragment fragment = new OrderFragment();
                    fragment.show(ft, "dialog");
            }

        return false;
    }

    public void Clear(View view) {
        db.clear();
    }

    public void PlaceOrder(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "This didn't work", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        db.clear();

    }
}



