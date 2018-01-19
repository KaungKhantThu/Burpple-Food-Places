package xyz.kkt.burpplefoodplaces.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 12/16/2017.
 */

public class BurppleDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "burpple.db";
    public static final int DB_VERSION = 1;

    public static final String SQL_CREATE_PROMOTION = "CREATE TABLE " + BurppleContract.PromotionEntry.TABLE_NAME + " (" +
            BurppleContract.PromotionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.PromotionEntry.COLUMN_PROMOTION_ID + " VARCHAR(256), " +
            BurppleContract.PromotionEntry.COLUMN_PROMOTION_IMAGE + " TEXT, " +
            BurppleContract.PromotionEntry.COLUMN_TITLE + " TEXT, " +
            BurppleContract.PromotionEntry.COLUMN_UNTIL + " TEXT, " +
            BurppleContract.PromotionEntry.COLUMN_PROMOTION_SHOP_ID + " VARCHAR(256), " +
            BurppleContract.PromotionEntry.COLUMN_IS_EXCLUSIVE + " BOOLEAN, " +

            " UNIQUE (" + BurppleContract.PromotionEntry.COLUMN_PROMOTION_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_PROMOTION_SHOP = "CREATE TABLE " + BurppleContract.PromotionShopEntry.TABLE_NAME + " (" +
            BurppleContract.PromotionShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.PromotionShopEntry.COLUMN_SHOP_ID + " VARCHAR(256), " +
            BurppleContract.PromotionShopEntry.COLUMN_SHOP_NAME + " TEXT, " +
            BurppleContract.PromotionShopEntry.COLUMN_AREA + " TEXT, " +

            " UNIQUE (" + BurppleContract.PromotionShopEntry.COLUMN_SHOP_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_PROMOTION_TERM = "CREATE TABLE " + BurppleContract.PromotionTermEntry.TABLE_NAME + " (" +
            BurppleContract.PromotionTermEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.PromotionTermEntry.COLUMN_TERM_IN_PROMOTION_ID + " VARCHAR(256), " +
            BurppleContract.PromotionTermEntry.COLUMN_TERM + " TEXT, " +

            " UNIQUE (" + BurppleContract.PromotionTermEntry.COLUMN_TERM + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_GUIDE = "CREATE TABLE " + BurppleContract.GuideEntry.TABLE_NAME + " (" +
            BurppleContract.GuideEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.GuideEntry.COLUMN_GUIDE_ID + " VARCHAR(256), " +
            BurppleContract.GuideEntry.COLUMN_GUIDE_IMAGE + " TEXT, " +
            BurppleContract.GuideEntry.COLUMN_GUIDE_TITLE + " TEXT, " +
            BurppleContract.GuideEntry.COLUMN_GUIDE_DESC + " TEXT, " +

            " UNIQUE (" + BurppleContract.GuideEntry.COLUMN_GUIDE_ID + ") ON CONFLICT REPLACE" +
            ");";

    public static final String SQL_CREATE_FEATURED = "CREATE TABLE " + BurppleContract.FeaturedEntry.TABLE_NAME + " (" +
            BurppleContract.FeaturedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BurppleContract.FeaturedEntry.COLUMN_FEATURED_ID + " VARCHAR(256), " +
            BurppleContract.FeaturedEntry.COLUMN_FEATURED_IMAGE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_FEATURED_TITLE + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_FEATURED_DESC + " TEXT, " +
            BurppleContract.FeaturedEntry.COLUMN_FEATURED_TAG + " TEXT, " +

            " UNIQUE (" + BurppleContract.FeaturedEntry.COLUMN_FEATURED_ID + ") ON CONFLICT REPLACE" +
            ");";


    public BurppleDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROMOTION);
        db.execSQL(SQL_CREATE_PROMOTION_SHOP);
        db.execSQL(SQL_CREATE_PROMOTION_TERM);

        db.execSQL(SQL_CREATE_GUIDE);
        db.execSQL(SQL_CREATE_FEATURED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDb, int newDb) {
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.PromotionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.PromotionShopEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.PromotionTermEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.GuideEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BurppleContract.FeaturedEntry.TABLE_NAME);
        onCreate(db);
    }
}
