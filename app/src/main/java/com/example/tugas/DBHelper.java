package com.example.tugas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    // Ganti nama agar emulator membuat file database baru yang bersih
    public static final String DB_NAME = "manajemen_tugas.db";
    public static final int DB_VERSION = 1;

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

        // 3. Membuat Tabel Matkul (Mata Kuliah & Tugas)
        sql = "CREATE TABLE matkul(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kode_matkul TEXT NOT NULL," +
                "nama_matkul TEXT NOT NULL," +
                "tugas TEXT," +
                "deadline TEXT" +
                ")";
        db.execSQL(sql);

        // --- INSERT DATA DUMMY AWAL --- //

        // Data Mahasiswa
        sql = "INSERT INTO mahasiswa(nim, nama) VALUES ('23001', 'Putu Dipa')";
        db.execSQL(sql);

        // Data Dosen
        sql = "INSERT INTO dosen(nidn, nama_dosen) VALUES ('11002', 'Bapak Dosen')";
        db.execSQL(sql);

        // Data Matkul & Tugas
        sql = "INSERT INTO matkul(kode_matkul, nama_matkul, tugas, deadline) VALUES ('IF101', 'Mobile Programming', 'Bikin CRUD SQLite', '2026-06-01')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Jika versi database (DB_VERSION) dinaikkan, tabel lama dihapus dan buat ulang
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        db.execSQL("DROP TABLE IF EXISTS dosen");
        db.execSQL("DROP TABLE IF EXISTS matkul");
        onCreate(db);
    }
}