package com.lfitzpatrick.finalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        ListView myListView = (ListView) findViewById(R.id.manageListView);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.lfitzpatrick.sharedpreferences", Context.MODE_PRIVATE);

        Map<String, String> usersMap = (Map<String, String>) sharedPreferences.getAll();

        Set<String> keys = usersMap.keySet();

        final ArrayList<String> userArrayList = new ArrayList<String>(asList(keys.toArray(new String[keys.size()])));

        Log.i("Info", keys.toString());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userArrayList);

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "User: " + userArrayList.get(i), Toast.LENGTH_SHORT).show();
            }
        });






    }
}
