package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues chapitre = new ContentValues();
        chapitre.put(ChapitreBaseSQLite.COL_NAME, "Chapitre 1");
        chapitre.put(ChapitreBaseSQLite.COL_DESC, "Chapitre 1 Description");

        getContentResolver().insert(ChapitreContentProvider.CONTENTURI, chapitre);

        setContentView(R.layout.activity_main);
    }
}