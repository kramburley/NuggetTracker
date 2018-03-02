package ca.bcit.nuggettracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by markv on 28/02/2018.
 * sources:
 *      https://medium.com/@ssaurel/learn-to-save-data-with-sqlite-on-android-b11a8f7718d3
 *      https://www.androidauthority.com/use-sqlite-store-data-app-599743/
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteExample.db";
    public static final String TABLE_NAME = "NuggetTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEAL = "meal";
    public static final String COLUMN_QTY = "quantity";
    public static final String COLUMN_SAUCE = "sauce";
    //public static final String COLUMN_SATISFACTION = "satisfaction";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEAL + " INTEGER, " +
                COLUMN_QTY + " INTEGER, " +
                COLUMN_SAUCE + " TEXT)"
                //+ COLUMN_SATISFACTION + " INTEGER)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //can implement upgrade commands here
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNugget(int meal, int qty, String sauce) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MEAL, meal);
        contentValues.put(COLUMN_QTY, qty);
        contentValues.put(COLUMN_SAUCE, sauce);
        //contentValues.put(COLUMN_SATISFACTION, satisfaction);

        db.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean updateNugget(Integer id, int meal, int qty, String sauce) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_MEAL, meal);
        contentValues.put(COLUMN_QTY, qty);
        contentValues.put(COLUMN_SAUCE, sauce);
        //contentValues.put(COLUMN_SATISFACTION, satisfaction);

        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );

        return true;
    }

    public int totalNuggets() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery(
                "SELECT SUM(COLUMN_QTY) " +
                        "FROM " + TABLE_NAME,
                null );

        //Cursor cur = db.rawQuery("SELECT SUM(myColumn) FROM myTable", null);

        if(cur.moveToFirst())
        {
            return cur.getInt(0);
        }
        return 0;
    }

    /*
    public int totalBreakfast() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(
                "SELECT SUM(COLUMN_QTY) " +
                        "FROM " + TABLE_NAME +
                        ", WHERE " + COLUMN_MEAL + " = Breakfast",
                null );

        //Cursor cur = db.rawQuery("SELECT SUM(myColumn) FROM myTable", null);

        return cur.getInt(0);
    }
    */
}
