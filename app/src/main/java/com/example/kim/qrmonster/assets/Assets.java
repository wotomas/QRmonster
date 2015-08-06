package com.example.kim.qrmonster.assets;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.activities.Main;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kim on 2015-08-06.
 */
public class Assets {
    private static SoundPool _soundPool;
    private static Bitmap _bitmap;

    public static void loadInit(Context context) {
        _bitmap = loadBitmap("welcome.png", false, context);
    }

    public static Bitmap loadBitmap(String filename, boolean transparency, Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(filename);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.i("Assets/loadBitmap: " + filename + " ", e.getMessage().toString());
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        if (transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());

        return bitmap;
    }

    public static int loadSound(String filename, Context context) {
        int soundID = 0;
        if (_soundPool == null) {
            _soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            soundID = _soundPool.load(context.getAssets().openFd(filename), 1);
        } catch (IOException e) {
            Log.i("Assets/loadBitmap: " + filename + " ", e.getMessage().toString());
        }

        return soundID;

    }

    public static void playSound(int soundID) {
        _soundPool.play(soundID, 1, 1, 1, 0, 1);
    }
}
