package xyz.kkt.burpplefoodplaces.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.burpplefoodplaces.persistence.BurppleContract;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class FeaturedVO {

    @SerializedName("burpple-featured-id")
    private String burppleFeaturedId;

    @SerializedName("burpple-featured-image")
    private String burppleFeaturedImage;

    @SerializedName("burpple-featured-title")
    private String burppleFeaturedTitle;

    @SerializedName("burpple-featured-desc")
    private String burppleFeaturedDesc;

    @SerializedName("burpple-featured-tag")
    private String burppleFeaturedTag;

    public String getBurppleFeaturedId() {
        return burppleFeaturedId;
    }

    public String getBurppleFeaturedImage() {
        return burppleFeaturedImage;
    }

    public String getBurppleFeaturedTitle() {
        return burppleFeaturedTitle;
    }

    public String getBurppleFeaturedDesc() {
        return burppleFeaturedDesc;
    }

    public String getBurppleFeaturedTag() {
        return burppleFeaturedTag;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_FEATURED_ID, burppleFeaturedId);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_FEATURED_IMAGE, burppleFeaturedImage);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_FEATURED_TITLE, burppleFeaturedTitle);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_FEATURED_DESC, burppleFeaturedDesc);
        contentValues.put(BurppleContract.FeaturedEntry.COLUMN_FEATURED_TAG, burppleFeaturedTag);

        return contentValues;
    }

    public static FeaturedVO parseFromCursor(Cursor cursor) {

        FeaturedVO featured = new FeaturedVO();

        featured.burppleFeaturedId = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_FEATURED_ID));
        featured.burppleFeaturedImage = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_FEATURED_IMAGE));
        featured.burppleFeaturedTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_FEATURED_TITLE));
        featured.burppleFeaturedDesc = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_FEATURED_DESC));
        featured.burppleFeaturedTag = cursor.getString(cursor.getColumnIndex(BurppleContract.FeaturedEntry.COLUMN_FEATURED_TAG));

        return featured;
    }

}
