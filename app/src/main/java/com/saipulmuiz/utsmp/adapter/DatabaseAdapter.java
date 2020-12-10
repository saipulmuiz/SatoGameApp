package com.saipulmuiz.utsmp.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.saipulmuiz.utsmp.data.RiwayatSkor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_satogameapp";
    private static final String TABLE_RIWAYAT_SKOR = "tbl_skor";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_SKOR = "skor";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_RIWAYAT_SKOR + "("
                + COLUMN_ID + " INTEGER not null primary key autoincrement," + COLUMN_NAMA + " varchar(100) not null,"
                + COLUMN_SKOR + " integer not null" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBAdapter", "Upgrade database dari versi " + oldVersion
                + " ke " + newVersion + ", akan menghapus semua data lama?");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RIWAYAT_SKOR);
        onCreate(db);
    }

    public void insertData(RiwayatSkor riwayatSkor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, riwayatSkor.getNama());
        values.put(COLUMN_SKOR, riwayatSkor.getSkor());

        db.insert(TABLE_RIWAYAT_SKOR, null, values);
        db.close();
    }

    public List<RiwayatSkor> getAllSkor() {
        List<RiwayatSkor> riwayatSkorList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RIWAYAT_SKOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                RiwayatSkor riwayatSkor = new RiwayatSkor(cursor.getString(1), cursor.getInt(2));
                riwayatSkorList.add(riwayatSkor);
            } while (cursor.moveToNext());
        }
        return riwayatSkorList;
    }

    public int getSkorCount() {
        String countQuery = "SELECT * FROM " + TABLE_RIWAYAT_SKOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
