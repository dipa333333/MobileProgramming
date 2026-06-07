package com.example.tugas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "manajemen_tugas.db";
    // Versi dinaikkan jadi 2 agar database lama direset
    public static final int DB_VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;

        // 1. Membuat Tabel Mahasiswa
        sql = "CREATE TABLE mahasiswa(" +
                "id INTEGER primary key autoincrement," +
                "nim text not null," +
                "nama text not null" +
                ")" ;
        db.execSQL(sql);

        // 2. Membuat Tabel Dosen
        sql = "CREATE TABLE dosen(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nidn TEXT NOT NULL," +
                "nama_dosen TEXT NOT NULL" +
                ")";
        db.execSQL(sql);

        // 3. Membuat Tabel Matkul (HANYA KODE DAN NAMA)
        sql = "CREATE TABLE matkul(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kode_matkul TEXT NOT NULL," +
                "nama_matkul TEXT NOT NULL" +
                ")";
        db.execSQL(sql);

        // --- INSERT DATA DUMMY AWAL --- //
        sql = "INSERT INTO mahasiswa(nim, nama) VALUES ('23001', 'Putu Dipa')";
        db.execSQL(sql);

        sql = "INSERT INTO dosen(nidn, nama_dosen) VALUES ('11002', 'Bapak Dosen')";
        db.execSQL(sql);

        // Data Matkul tanpa tugas & deadline
        sql = "INSERT INTO matkul(kode_matkul, nama_matkul) VALUES ('IF101', 'Mobile Programming')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        db.execSQL("DROP TABLE IF EXISTS dosen");
        db.execSQL("DROP TABLE IF EXISTS matkul");
        onCreate(db);
    }
}