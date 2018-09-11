package com.example.user.kamusdicoding;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TITLE;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TRANSLATION;
import static com.example.user.kamusdicoding.DatabaseKontrak.TABLE_NAME_INDONESIA;
import java.util.ArrayList;

public class KamusHelpIndonesia
{
    private Context context;
    private DatabaseHelp databaseHelp;
    private SQLiteDatabase database;

    public KamusHelpIndonesia (Context context) {
        this.context = context;
    }

    public KamusHelpIndonesia open() throws SQLiteException {
        databaseHelp = new DatabaseHelp(context);
        database = databaseHelp.getReadableDatabase();
        return this;
    }

    public void close() {
        databaseHelp.close();
    }

    public ArrayList<Kamus> getData() {
        String hasil = null;
        Cursor cursor = database.query(TABLE_NAME_INDONESIA, null, null, null,
                null, null, _ID + " ASC" , null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;

        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                kamus.setTerjemahan(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATION)));
                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
            cursor.close();
            return arrayList;
    }

    public ArrayList<Kamus> getDataByName(String title) {
        String hasil = null;
        Cursor cursor  = database.query(DatabaseKontrak.TABLE_NAME_INDONESIA, null, TITLE+" LIKE ?", new String[]{title} ,
    null, null, _ID+ " ASC", null);
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                kamus.setTerjemahan(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATION)));
                arrayList.add(kamus);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertTransaction(Kamus kamus) {
        Log.d("TAG", "Insert transaction executed"+ kamus.getJudul());
        String sql = "INSERT INTO "+ TABLE_NAME_INDONESIA + " (" + TITLE + ", " + TRANSLATION + ") VALUES (?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, kamus.getJudul());
        statement.bindString(2, kamus.getTerjemahan());
        statement.execute();
        statement.clearBindings();

    }
}
