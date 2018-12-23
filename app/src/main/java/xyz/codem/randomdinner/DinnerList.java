package xyz.codem.randomdinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import xyz.codem.randomdinner.database.DataBaseHelper;
import xyz.codem.randomdinner.database.DbSchema;
import xyz.codem.randomdinner.database.mCursorWrapper;

import static xyz.codem.randomdinner.database.DbSchema.Table.Cols.*;

public class DinnerList {
    List<DinnerArray> allEntryList = new ArrayList<>();
    private SQLiteDatabase database;
    private static DinnerList list;
    private Context context;


    private DinnerList(Context context){
        this.context = context.getApplicationContext();
        database = new DataBaseHelper(context).getReadableDatabase();
    }
    public static DinnerList get(Context context) {
        if (list == null) {
            list = new DinnerList(context);
        }
        return list;
    }

    public void addNewArray(DinnerArray entry){
        ContentValues values = getContentValues(entry);
        database.insert(DbSchema.Table.NAME, null, values);
    }

    public void removeArray(DinnerArray array){
        String uuidString = array.getId().toString();
        database.delete(DbSchema.Table.NAME, UUID + " = ?", new String[] {uuidString});
    }

    public int getSize(){
        return getEntry().size();
    }

    public List<DinnerArray> getEntry(){
        List<DinnerArray> arrays = new ArrayList<>();
        mCursorWrapper cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                arrays.add(0, cursor.getItems());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return arrays;
    }

    private mCursorWrapper query(String clause, String[] args){
        Cursor cursor = database.query(
                DbSchema.Table.NAME,
                null, // Columns - null selects all columns
                clause,
                args,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new mCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(DinnerArray array) {
        ContentValues values = new ContentValues();
        values.put(UUID, array.getId().toString());
        values.put(TITLE, array.getName());
        array.getLastTime().getTime();
        values.put(DATE, array.getLastTime().getTime());
        values.put(RESULT, array.getLastResult());
        values.put(ENTRY, array.allEntry());
        return values;
    }
}
