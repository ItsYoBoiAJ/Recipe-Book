package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, description;
    Button add, view, delete, update, clear;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.e1);
        description = findViewById(R.id.e2);
        add = findViewById(R.id.b1);
        view = findViewById(R.id.b2);
        delete = findViewById(R.id.b3);
        update = findViewById(R.id.b4);
        clear = findViewById(R.id.b5);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recipe recipe;

                try {
                    recipe = new Recipe(1,name.getText().toString(), description.getText().toString());
                    Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    recipe = new Recipe(1,"error","error in storing description");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this,"Recipe",null,1);
                databaseHelper.addrecord(recipe);


                name.setText("");
                description.setText("");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openListViewPage();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this, "Rcipe", null, 1);

                String input = name.getText().toString();
                databaseHelper.delete(input);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();



                name.setText("");
                description.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = name.getText().toString();
                String newDes = description.getText().toString();

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this,"Recipe", null, 1);
                databaseHelper.update(newName, newDes);
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();



                name.setText("");
                description.setText("");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.setText("");
                description.setText("");

            }
        });
    }


    public void openListViewPage() {
        Intent intent = new Intent(this, ListViewPage.class);
        startActivity(intent);

    }
}
