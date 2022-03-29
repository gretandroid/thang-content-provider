package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChapitreContentProvider extends ContentProvider {

    // from AndroidManifest.provider.android:authorities
    public static final Uri CONTENTURI =  Uri.parse("content://com.example.contentprovider.ChapitreContentProvider");

    public static final String CONTENT_PROVIDER_NIME_SINGLE = "vnd.android.cursor.item/vnd.com.example.contentprovider.chapitre";
    public static final String CONTENT_PROVIDER_NIME_COLLECTION = "vnd.android.cursor.item/vnd.com.example.contentprovider.chapitres";

    private ChapitreBaseSQLite dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new ChapitreBaseSQLite(getContext(),
                ChapitreBaseSQLite.NAME_DB,
                null, ChapitreBaseSQLite.VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] columns,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long id = getId(uri);
        if (id < 0) {
            return db.query(ChapitreBaseSQLite.TABLE_CHAPITRE,
                    columns,
                    selection,
                    selectionArgs, null, null, sortOrder);
        }
        else {
            return db.query(ChapitreBaseSQLite.TABLE_CHAPITRE,
                    columns,
                    ChapitreBaseSQLite.COL_ID  + " = " + id ,
                    selectionArgs, null, null, sortOrder);
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            long id;
            id = db.insertOrThrow(ChapitreBaseSQLite.TABLE_CHAPITRE, null, values);

            if (id == -1) {
                throw new RuntimeException("Fail insert into " + ChapitreBaseSQLite.TABLE_CHAPITRE);
            }
            else {
                Log.d("App", "uri=" + uri.toString() + "/" + id);
                return ContentUris.withAppendedId(uri, id);
            }
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * Recuperer a dernier partie URI
     */

    private long getId(Uri uri) {
        String  lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            return Long.parseLong(lastPathSegment);
        }

        return -1;
    }
}
