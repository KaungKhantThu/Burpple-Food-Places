package xyz.kkt.burpplefoodplaces.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.burpplefoodplaces.persistence.BurppleContract;

/**
 * Created by Lenovo on 1/17/2018.
 */

public class GuideVO {

    @SerializedName("burpple-guide-id")
    private String burppleGuideId;

    @SerializedName("burpple-guide-image")
    private String burppleGuideImage;

    @SerializedName("burpple-guide-title")
    private String burppleGuideTitle;

    @SerializedName("burpple-guide-desc")
    private String burppleGuideDesc;

    public String getBurppleGuideId() {
        return burppleGuideId;
    }

    public String getBurppleGuideImage() {
        return burppleGuideImage;
    }

    public String getBurppleGuideTitle() {
        return burppleGuideTitle;
    }

    public String getBurppleGuideDesc() {
        return burppleGuideDesc;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.GuideEntry.COLUMN_GUIDE_ID, burppleGuideId);
        contentValues.put(BurppleContract.GuideEntry.COLUMN_GUIDE_IMAGE, burppleGuideImage);
        contentValues.put(BurppleContract.GuideEntry.COLUMN_GUIDE_TITLE, burppleGuideTitle);
        contentValues.put(BurppleContract.GuideEntry.COLUMN_GUIDE_DESC, burppleGuideDesc);

        return contentValues;

    }

    public static GuideVO parseFromCursor(Cursor cursor) {

        GuideVO guide = new GuideVO();

        guide.burppleGuideId = cursor.getString(cursor.getColumnIndex(BurppleContract.GuideEntry.COLUMN_GUIDE_ID));
        guide.burppleGuideImage = cursor.getString(cursor.getColumnIndex(BurppleContract.GuideEntry.COLUMN_GUIDE_IMAGE));
        guide.burppleGuideTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.GuideEntry.COLUMN_GUIDE_TITLE));
        guide.burppleGuideDesc = cursor.getString(cursor.getColumnIndex(BurppleContract.GuideEntry.COLUMN_GUIDE_DESC));

        return guide;
    }

}
