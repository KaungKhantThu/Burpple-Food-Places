package xyz.kkt.burpplefoodplaces.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Lenovo on 12/16/2017.
 */

public class BurppleProvider extends ContentProvider {

    public static final int PROMOTION = 100;
    public static final int PROMOTION_SHOP = 200;
    public static final int PROMOTION_TERM = 300;
    public static final int GUIDE = 400;
    public static final int FEATURED = 500;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder sPromotionWithShop_IJ;

    static {
        sPromotionWithShop_IJ = new SQLiteQueryBuilder();
        sPromotionWithShop_IJ.setTables(
                BurppleContract.PromotionEntry.TABLE_NAME + " INNER JOIN " +
                        BurppleContract.PromotionShopEntry.TABLE_NAME + " ON " +
                        BurppleContract.PromotionEntry.TABLE_NAME + "." + BurppleContract.PromotionEntry.COLUMN_PROMOTION_SHOP_ID + " = " +
                        BurppleContract.PromotionShopEntry.TABLE_NAME + "." + BurppleContract.PromotionShopEntry.COLUMN_SHOP_ID
        );
    }


    private BurppleDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);//passing initial condition
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTION, PROMOTION);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTION_SHOP, PROMOTION_SHOP);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_PROMOTION_TERM, PROMOTION_TERM);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_GUIDE, GUIDE);
        uriMatcher.addURI(BurppleContract.CONTENT_AUTHORITY, BurppleContract.PATH_FEATURED, FEATURED);
        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case PROMOTION:
                return BurppleContract.PromotionEntry.TABLE_NAME;
            case PROMOTION_SHOP:
                return BurppleContract.PromotionShopEntry.TABLE_NAME;
            case PROMOTION_TERM:
                return BurppleContract.PromotionTermEntry.TABLE_NAME;
            case GUIDE:
                return BurppleContract.GuideEntry.TABLE_NAME;
            case FEATURED:
                return BurppleContract.FeaturedEntry.TABLE_NAME;
        }
        return null;
    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case PROMOTION:
                return BurppleContract.PromotionEntry.CONTENT_URI;
            case PROMOTION_SHOP:
                return BurppleContract.PromotionShopEntry.CONTENT_URI;
            case PROMOTION_TERM:
                return BurppleContract.PromotionTermEntry.CONTENT_URI;
            case GUIDE:
                return BurppleContract.GuideEntry.CONTENT_URI;
            case FEATURED:
                return BurppleContract.FeaturedEntry.CONTENT_URI;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new BurppleDBHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
//        Cursor queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri), projection
//                , selection, selectionArgs, null, null, sortOrder);
//        if (getContext() != null) {
//            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);//retelling the cursor to point to the query uri if the uri is changed
//        }
//        return queryCursor;
        Cursor queryCursor;
        switch (sUriMatcher.match(uri)) {
            case PROMOTION:
                queryCursor = sPromotionWithShop_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
        }


        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case PROMOTION:
                return BurppleContract.PromotionEntry.DIR_TYPE;
            case PROMOTION_SHOP:
                return BurppleContract.PromotionShopEntry.DIR_TYPE;
            case PROMOTION_TERM:
                return BurppleContract.PromotionTermEntry.DIR_TYPE;
            case GUIDE:
                return BurppleContract.GuideEntry.DIR_TYPE;
            case FEATURED:
                return BurppleContract.FeaturedEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }


        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);

        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            //db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }
}
