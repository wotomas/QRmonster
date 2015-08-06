package com.example.kim.qrmonster.assets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by kim on 2015-08-06.
 */
public class MonsterImageView extends ImageView {

    private boolean _list = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MonsterImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MonsterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MonsterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MonsterImageView(Context context) {
        super(context);
        setBackgroundColor(0xFFFFFF);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Drawable drawable = getDrawable();

        if(drawable == null) {
            return;
        }
        if(getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        if(_list) {
            Bitmap monsterCropped = getMonsterListCroppedBitmap(bitmap);
            canvas.drawBitmap(monsterCropped, 50, 300 - monsterCropped.getHeight() , null);
            Log.i("MonsterImageView/onDraw", "monsterCropped Image Width and Height: " + monsterCropped.getWidth() + " " + monsterCropped.getHeight());
        } else {
            Bitmap monsterCropped = getMonsterCroppedBitmap(bitmap);
            canvas.drawBitmap(monsterCropped, 0,750 - monsterCropped.getHeight(), null);
            Log.i("MonsterImageView/onDraw", "monsterCropped Image Width and Height: " + monsterCropped.getWidth() + " " + monsterCropped.getHeight());
        }

    }

    private Bitmap getMonsterListCroppedBitmap(Bitmap bitmap) {
        Bitmap finalBitmap = bitmap;
        int width = finalBitmap.getWidth();
        int height = finalBitmap.getHeight();

        Log.i("MonsterImageView/getMonsterCroppedBitmap", "Initial Image Width and Height: " + finalBitmap.getWidth() + " " + finalBitmap.getHeight());

        Bitmap croppedImage = Bitmap.createBitmap(finalBitmap, 0, 0, bitmap.getWidth() /4 -4, bitmap.getHeight()/4 -4);
        Log.i("MonsterImageView/getMonsterCroppedBitmap", "Cropped Image Width and Height: " + croppedImage.getWidth() + " " + croppedImage.getHeight());

        Bitmap lastBitmap = Bitmap.createScaledBitmap(croppedImage, 200, 200 * height / width, false);
        Log.i("MonsterImageView/getMonsterCroppedBitmap", "lastBitmap Image Width and Height: " + lastBitmap.getWidth() + " " + lastBitmap.getHeight());

        Canvas canvas = new Canvas(lastBitmap);
        canvas.drawBitmap(lastBitmap, 0, 0, null);

        return lastBitmap;
    }

    public void listMode(Boolean value) {
        _list = value;
    }

    private Bitmap getMonsterCroppedBitmap(Bitmap bitmap) {
        Bitmap finalBitmap = bitmap;
        int width = finalBitmap.getWidth();
        int height = finalBitmap.getHeight();

        Log.i("MonsterImageView/getMonsterCroppedBitmap", "Initial Image Width and Height: " + finalBitmap.getWidth() + " " + finalBitmap.getHeight());

        Bitmap croppedImage = Bitmap.createBitmap(finalBitmap, 0, 0, bitmap.getWidth() /4 -4, bitmap.getHeight()/4 -4);
        Log.i("MonsterImageView/getMonsterCroppedBitmap", "Cropped Image Width and Height: " + croppedImage.getWidth() + " " + croppedImage.getHeight());

        Bitmap lastBitmap = Bitmap.createScaledBitmap(croppedImage, 500, 500 * height / width, false);
        Log.i("MonsterImageView/getMonsterCroppedBitmap", "lastBitmap Image Width and Height: " + lastBitmap.getWidth() + " " + lastBitmap.getHeight());

        Canvas canvas = new Canvas(lastBitmap);
        canvas.drawBitmap(lastBitmap, 0, 0, null);

        return lastBitmap;
    }
}
