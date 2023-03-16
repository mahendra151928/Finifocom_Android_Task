package com.example.finifocom_android_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

  private  EditText etName,etPassword;
    private Button btnLogin;
    String name;
    private Realm realm;
    ArrayList personNames;
    ArrayList personAges;
    List<DataModal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        realm = Realm.getDefaultInstance();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name  = etName.getText().toString();
                String password = etPassword.getText().toString();

                if (!name.equalsIgnoreCase("") || !name.isEmpty()
                        && !password.equalsIgnoreCase("") || !password.isEmpty()){

                    validatepass(password);

                }

                }

        });


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
        public void validatepass(String password) {

        // check for pattern
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");


        if (!lowercase.matcher(password).find()) {
            Toast.makeText(this, "lowercase", Toast.LENGTH_SHORT).show();

        } else {

        }

        // if uppercase character is not present
        if (!uppercase.matcher(password).find()) {
            Toast.makeText(this, "uppercase", Toast.LENGTH_SHORT).show();

        } else {
            // if uppercase character is  present

        }
        // if digit is not present
        if (!digit.matcher(password).find()) {
            Toast.makeText(this, "digits", Toast.LENGTH_SHORT).show();

        } else {
            // if digit is present

        }

            if (name.length() < 10) {
                Toast.makeText(this, "please enter atlest 10 characters", Toast.LENGTH_SHORT).show();
            }
        // if password length is less than 8
        if (password.length() < 7) {
            Toast.makeText(this, "please enter atlest 8 characters", Toast.LENGTH_SHORT).show();
        } else {
            if (name.equalsIgnoreCase("Fininfocom") && password.equals("Fin@123")) {
                addDataToDatabase(name, password);
                Toast.makeText(MainActivity.this, "Course added to database..", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etPassword.setText("");
            }else {
                Toast.makeText(this, "please enter correct email and password", Toast.LENGTH_SHORT).show();
            }

        }




    }

    private void addDataToDatabase(String name, String password) {

        List<DataModal> dataModalNames=new ArrayList<>();

        list = new ArrayList<>();
        list= getData();


        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // inside on execute method we are calling a method
                // to copy to real m database from our modal class.

              /*  Number id = realm.where(DataModal.class).max("id");

                // on below line we are
                // creating a variable for our id.
                long nextId;

                // validating if id is null or not.
                if (id == null) {
                    // if id is null
                    // we are passing it as 1.
                    nextId = 1;
                } else {
                    // if id is not null then
                    // we are incrementing it by 1
                    nextId = id.intValue() + 1;
                }*/

                for (int i=0;i<list.size();i++){

                    DataModal modal = new DataModal();
                    modal.setId(list.get(i).getId());
                    modal.setName(list.get(i).getName());
                    modal.setAge(list.get(i).getAge());
                    modal.setCity(list.get(i).getCity());

                    realm.insertOrUpdate(modal);
                }

            }
        });

        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);

}
    private List<DataModal> getData()
    {
        List<DataModal> list = new ArrayList<>();
        list.add(new DataModal(1,"Raju",
                "25",
                "Vijayawada"));
        list.add(new DataModal(2,"Mahendra",
                "26",
                "Tanuku"));
        list.add(new DataModal(3,"Sai",
                "23",
                "Savaram"));

        return list;
    }
}

