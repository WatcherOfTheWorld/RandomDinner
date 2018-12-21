package xyz.codem.randomdinner.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import xyz.codem.randomdinner.database.DbSchema.Table;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table.NAME + "(" +
                " _id integer primary key autoincrement, " +
                Table.Cols.UUID + ", " +
                Table.Cols.TITLE + ", " +
                Table.Cols.DATE + ", " +
                Table.Cols.RESULT +", "+
                Table.Cols.ENTRY+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}