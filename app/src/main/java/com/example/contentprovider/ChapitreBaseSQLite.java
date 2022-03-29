package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Cette classe fourni un helper
 * qui contient deux méthodes qui font partis du cycle de vie de
 * la classe {@link SQLiteOpenHelper}
 *
 * Lorsque on ouvre la base de donnée en écriture alors la méthode
 * {@link #onCreate(SQLiteDatabase)} est appelé pour créer le schema de BDD
 *
 * De plus, si version (schéma) la BDD change,
 * méthode {@link #onUpgrade(SQLiteDatabase, int, int)} est appélé
 */
public class ChapitreBaseSQLite extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NAME_DB = "Chapitre.db";
    public static final String TABLE_CHAPITRE = "table_chapitre";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DESC = "description";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_CHAPITRE +
            " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT NOT NULL, " +
                    COL_DESC + " TEXT NOT NULL " +
            ") ";


    public ChapitreBaseSQLite(@Nullable Context context,
                              @Nullable String name,
                              @Nullable SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // just an example, we can add COL, UPDATE Data without recreate table
        db.execSQL("DROP TABLE " + TABLE_CHAPITRE);
        onCreate(db);
    }
}
