package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RECIPE = "RECIPE";
    public static final String ID = "ID";
    public static final String RECIPE_NAME = "RECIPE_NAME";
    public static final String RECIPE_DESCRIPTION = "RECIPE_DESCRIPTION";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Recipe", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CreateTable = "CREATE TABLE " + RECIPE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECIPE_NAME + " TEXT, " + RECIPE_DESCRIPTION + " TEXT)";
        db.execSQL(CreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE);

    }

    public boolean addrecord(Recipe recipe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RECIPE_NAME, recipe.getName());
        cv.put(RECIPE_DESCRIPTION, recipe.getDescription());

        long insert = db.insert(RECIPE, null, cv);

        if(insert == -1) {
            return false;
        }
        return true;
    }

    public List<String> getalldata(){
        List<String> display = new ArrayList<>();
        String query = "SELECT * FROM " + RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                //int recipeID = c.getInt(0);
                String recipeName = c.getString(1);
                //String recipeDescription = c.getString(2);

               //Recipe newRecipe = new Recipe(recipeID, recipeName, recipeDescription);

               display.add(recipeName);
            }while(c.moveToNext());
        }
        c.close();
        db.close();

        return display;
    }


    public void delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + RECIPE + " WHERE " + RECIPE_NAME + "=\"" + name + "\";");
    }

    public boolean update(String name, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RECIPE_NAME, name);
        cv.put(RECIPE_DESCRIPTION, des);
        db.update(RECIPE, cv, RECIPE_NAME + "=?", new String[]{name});
        return true;
    }

    public long returnid(long index)
    {
        long i=0;
        String query = "SELECT * FROM " + RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {

                int recipeID = c.getInt(0);
                if(i==index)
                    return recipeID;
                i++;
            }while(c.moveToNext());
        }
        c.close();
        db.close();


        return 0;
    }

    public List<String> returnname(long id)
    {
        List<String> val = new ArrayList<>();
        String query = "SELECT * FROM " + RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                int recipeID = c.getInt(0);
                String recipeName = c.getString(1);
                if(recipeID == id){
                    val.add(recipeName);
                    return val;
                }

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return val;
    }

    public List<String> returndes(long id)
    {
        List<String> val = new ArrayList<>();
        String query = "SELECT * FROM " + RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                int recipeID = c.getInt(0);
                String recipeDescription = c.getString(2);
                if(recipeID == id){
                    val.add(recipeDescription);
                    return val;
                }

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return val;
    }
}
