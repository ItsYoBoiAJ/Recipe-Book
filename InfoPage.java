package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.StringJoiner;

import static com.example.app.DatabaseHelper.RECIPE;
import static java.lang.Integer.parseInt;

public class InfoPage extends AppCompatActivity {

    TextView rname;
    TextView description;
    String s,finals;
    Button tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);


        DatabaseHelper databaseHelper = new DatabaseHelper(InfoPage.this, "Recipe", null, 1);



        rname  = findViewById(R.id.t1);
        description = findViewById(R.id.t2);
        tutorial = findViewById(R.id.b8);
        Intent intent=getIntent();
        long id = intent.getLongExtra("id",0);

        final List<String> name = databaseHelper.returnname(id);

        s = name.toString();
        finals = s.substring(1,s.length()-1);
        rname.setText(finals);

        List<String> des = databaseHelper.returndes(id);

        s = des.toString();
        finals = s.substring(1,s.length()-1);
        description.setText(finals);

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/results?search_query=";
                s = name.toString();
                finals = s.substring(1,s.length()-1);
                url = url + finals;
                Uri uri = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });




    }



}
