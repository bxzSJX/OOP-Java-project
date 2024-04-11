package com.example.oopandroidapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText editCity;
    private Button btnSearch;
    private ImageView imageDelete;
    private RecyclerView recyclerView;
    private ArrayList<String> a = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private String cityname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editCity = findViewById(R.id.e1);
        btnSearch = findViewById(R.id.b1);
        imageDelete = findViewById(R.id.imageDelete);
        recyclerView = findViewById(R.id.r1);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 20);
        recyclerView.setLayoutManager(gridLayoutManager);
        MainSearchAdapter mainSearchAdapter = new MainSearchAdapter(a,this);
        recyclerView.setAdapter(mainSearchAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityname = editCity.getText().toString();
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                mainSearchAdapter.notifyDataSetChanged();


                startActivity(intent);
                addToLatestSearch();

            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.clear();
                mainSearchAdapter.notifyDataSetChanged();
            }
        });

    }
    public void addToLatestSearch(){
        a.add(cityname);
        City city = new City(cityname);
        editCity.setText("");
    }

}
