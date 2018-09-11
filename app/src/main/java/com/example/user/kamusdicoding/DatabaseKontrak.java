package com.example.user.kamusdicoding;

import android.provider.BaseColumns;

public class DatabaseKontrak {
    static String TABLE_NAME_INDONESIA = "indonesia_english";
    static String TABLE_NAME_ENGLISH = "englishindonesiaFragment";

    static final class DictionaryColumns implements BaseColumns {
        static String TITLE = "Judul";
        static String TRANSLATION = "Terjemahan";
    }
}
