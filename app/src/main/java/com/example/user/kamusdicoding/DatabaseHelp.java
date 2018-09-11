package com.example.user.kamusdicoding;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TITLE;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns.TRANSLATION;
import static com.example.user.kamusdicoding.DatabaseKontrak.DictionaryColumns._ID;

public class DatabaseHelp extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbDictionary";
    private static final int DATABASE_VERSI = 1;

    public static String CREATE_TABLE_INDONESIA = "create table " + DatabaseKontrak.TABLE_NAME_INDONESIA
            + " (" + _ID + "integer primary key autoincrement, " +
            TITLE + "text not null, " +
            TRANSLATION + " text not null);";

    public static String CREATE_TABLE_INGGRIS = "create table " + DatabaseKontrak.TABLE_NAME_ENGLISH
            + " (" + _ID + "integer primary key autoincrement, " +
            TITLE + " text not null, " +
            TRANSLATION + "text not null);";

    public DatabaseHelp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSI);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_INDONESIA);
        sqLiteDatabase.execSQL(CREATE_TABLE_INGGRIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseKontrak.TABLE_NAME_INDONESIA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseKontrak.TABLE_NAME_ENGLISH);
        onCreate(sqLiteDatabase);
    }
}
