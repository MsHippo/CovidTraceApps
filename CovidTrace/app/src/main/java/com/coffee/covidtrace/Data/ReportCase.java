package com.coffee.covidtrace.Data;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.Editable;
import android.util.Base64;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

@Entity(tableName = "report_case_table")
public class ReportCase {

    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String image;
    public String date;
    public int user_id;
//    int status;

    public static final int COL_ID = 0;
    public static final int COL_TITLE = 1;
    public static final int COL_IMAGE = 2;


//    public ReportCase(Cursor cursor) {
//        this.title = cursor.getString(COL_TITLE);
//        this.image = cursor.getString(COL_IMAGE);
//    }

    public ReportCase(String title, Bitmap image, String date, int user_id) {
        this.title = title;
        this.image = bitmapToString(resizeBitmap(image));
        this.date = date;
        this.user_id = user_id;
    }
    public ReportCase(){}

    public String getTitle() {
        return this.title;
    }

    public Bitmap getImage() {
        return stringToBitmap(this.image);
    }

    public String getImageAsString() {
        return this.image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }


}
