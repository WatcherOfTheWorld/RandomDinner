package xyz.codem.randomdinner.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import xyz.codem.randomdinner.DinnerArray;
import xyz.codem.randomdinner.database.DbSchema.Table;

import java.util.Date;
import java.util.UUID;

import xyz.codem.randomdinner.DinnerList;

public class mCursorWrapper extends CursorWrapper {

    public mCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DinnerArray getItems() {
        String uuidString = getString(getColumnIndex(Table.Cols.UUID));
        String title = getString(getColumnIndex(Table.Cols.TITLE));
        long date = getLong(getColumnIndex(Table.Cols.DATE));
        String result = getString(getColumnIndex(Table.Cols.RESULT));
        String entry = getString(getColumnIndex(Table.Cols.ENTRY));

        DinnerArray dinners = new DinnerArray(UUID.fromString(uuidString));
        //Crime crime = new Crime(UUID.fromString(uuidString));
        dinners.setName(title);
        dinners.setLastTime(new Date(date));
        dinners.setLastResult(result);
        //TODO: add entry to array
        String[] array = entry.split(",");
        for(String item: array){
            dinners.addEntry(item);
        }

        return dinners;
        //return crime;
    }
}
