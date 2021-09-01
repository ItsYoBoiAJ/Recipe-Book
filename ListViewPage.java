package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import static com.example.app.DatabaseHelper.RECIPE;

public class ListViewPage extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_page);

        lv = findViewById(R.id.list);


        DatabaseHelper databaseHelper = new DatabaseHelper(ListViewPage.this, "Recipe", null, 1);

        List<String> all = databaseHelper.getalldata();
        adapter = new ArrayAdapter<String>(ListViewPage.this,android.R.layout.simple_list_item_1, all);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0) {
                    DatabaseHelper d = new DatabaseHelper(ListViewPage.this, "Recipe", null, 1);
                    long k = d.returnid(id);



                    Intent intent = new Intent(view.getContext(), InfoPage.class);
                    intent.putExtra("id",k);
                    startActivity(intent);

                }
            }
        });


    }
}
