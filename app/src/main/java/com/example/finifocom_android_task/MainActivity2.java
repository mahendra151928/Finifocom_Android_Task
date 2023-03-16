package com.example.finifocom_android_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.realm.Realm;

public class MainActivity2 extends AppCompatActivity {

    List<DataModal> dataModals;

    private Realm realm;
    private RecyclerView coursesRV;
    private CourseRVAdapter courseRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // on below lines we are initializing our variables.
        coursesRV = findViewById(R.id.idRVCourses);
        realm = Realm.getDefaultInstance();
        dataModals = new ArrayList<>();

        // calling a method to load
        // our recycler view with data.
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {

        dataModals = realm.where(DataModal.class).findAll();
        courseRVAdapter = new CourseRVAdapter(dataModals, this);
        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        coursesRV.setAdapter(courseRVAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:

                dataModals = realm.where(DataModal.class).sort("name").findAll();
                adapterList();
                Toast.makeText(getApplicationContext(), "name", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                dataModals = realm.where(DataModal.class).sort("age").findAll();
                adapterList();
                Toast.makeText(getApplicationContext(), "age", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                dataModals = realm.where(DataModal.class).sort("city").findAll();
                adapterList();
                Toast.makeText(getApplicationContext(), "city", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void adapterList() {

        courseRVAdapter = new CourseRVAdapter(dataModals, this);
        // on below line we are setting layout manager to our recycler view.
        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        // at last we are setting adapter to our recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }


}