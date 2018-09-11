package com.example.user.kamusdicoding;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TITLE;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TRANSLATION;
import static com.example.user.kamusdicoding.DatabaseKontrak.TABLE_NAME_ENGLISH;
public class KamusHelpEnglish {
    private Context context;
    private DatabaseHelp databaseHelp;
    private SQLiteDatabase database;

    public KamusHelpEnglish(Context context) {
        this.context = context;
    }

    public KamusHelpEnglish open() throws SQLException {
        databaseHelp = new DatabaseHelp(context);
        database = databaseHelp.getReadableDatabase();
        return this;
    }

    public void close() {
        databaseHelp.close();
    }

    public ArrayList<Kamus> getData() {
    String result = null;
    Cursor cursor;

    cursor = database.query(TABLE_NAME_ENGLISH, null, null, null, null, null, _ID + " ASC", null);
    cursor.moveToFirst();
    ArrayList<Kamus> arrayList = new ArrayList<>();
    Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                kamus.setTerjemahan(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATION)));
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<Kamus> getDataByName(String title) {
        String hasil = null;
        Cursor cursor = database.query(TABLE_NAME_ENGLISH, null, TITLE +" LIKE ?", new String[] {
            title}, null, null, _ID + " ASC", null);
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

    public void insertTransaction(Kamus kamusItem) {
        Log.d("TAG", "insertTransaction executed " + kamusItem.getJudul());
        String sql = "INSERT INTO " + TABLE_NAME_ENGLISH + " (" + TITLE + ", " + TRANSLATION
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusItem.getJudul());
        stmt.bindString(2, kamusItem.getTerjemahan());
        stmt.execute();
        stmt.clearBindings();


    }
}
