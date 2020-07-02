package com.example.carsellerxk.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "userdata.db";
    public static final int DB_VERSION = 1;

    public SQLiteHelper(@Nullable Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favorites(userEmail,postTitle)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favorites");
    }

    public boolean saveToFavorites(String userEmail, String postId) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userEmail", userEmail);
        contentValues.put("postTitle", postId);
        long rowsAffected = database.insert("favorites", null, contentValues);
        if (rowsAffected == -1)
            return false;
        else return true;
    }

    public List<String> getFavorites(String userEmail){
        List<String> userFavoritePosts = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM favorites WHERE userEmail=?",new String[]{userEmail});
        while(cursor.moveToNext()){
            userFavoritePosts.add(cursor.getString(1));
        }
        return userFavoritePosts;
    }
}
