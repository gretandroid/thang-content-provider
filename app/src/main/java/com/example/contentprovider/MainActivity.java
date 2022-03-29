package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues chapitre = new ContentValues();
        chapitre.put(ChapitreBaseSQLite.COL_NAME, "Chapitre 1");
        chapitre.put(ChapitreBaseSQLite.COL_DESC, "Chapitre 1 Description");

        getContentResolver().insert(ChapitreContentProvider.CONTENTURI, chapitre);

        // display
        display();
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("Range")
    private void display() {
        Cursor cursor = getContentResolver().query(ChapitreContentProvider.CONTENTURI,
                        null, null, null, null);
        if (cursor.moveToFirst()) {
            String result = null;
             do {
                 result = new StringBuilder()
                         .append(ChapitreBaseSQLite.COL_ID + " : ")
                         .append(cursor.getString(cursor.getColumnIndex(ChapitreBaseSQLite.COL_ID)) + " ")
                         .append(ChapitreBaseSQLite.COL_NAME + " : ")
                         .append(cursor.getString(cursor.getColumnIndex(ChapitreBaseSQLite.COL_NAME)) + " ")
                         .append(ChapitreBaseSQLite.COL_DESC + " : ")
                         .append(cursor.getString(cursor.getColumnIndex(ChapitreBaseSQLite.COL_DESC)) + " ")
                         .toString();
                 Log.d("App", result);
             } while (cursor.moveToNext());
        }
    }
}