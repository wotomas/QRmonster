package com.example.kim.catchmonster.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.kim.catchmonster.assets.Sound;
import com.example.kim.catchmonster.screens.PlayScreen;
import com.example.kim.catchmonster.screens.Screen;
import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.controller.CatchedMonsterController;
import com.example.kim.qrmonster.controller.MonsterController;
import com.example.kim.qrmonster.units.Monster;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ActionBarActivity {
    public static final String LOG_ID = "Greenie";
    static final float EXPECTED_DENSITY = 315.0f;  // original target density of runtime device
    static final float EXPECTED_WIDTH = 720.0f;  // original target width of runtime device
    public int TS_NORMAL; // normal text size
    public int TS_BIG; // large text size
    public float densityscalefactor;
    public float sizescalefactor;
    Screen entryScreen;
    DisplayMetrics dm;
    PlayScreen playScreen;
    Screen currentScreen;
    FullScreenView mainView;
    Typeface gamefont;
    public SoundPool soundpool = null;
    Map<Sound, Integer> soundMap = null;

    /**
     * Initialize the activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            super.onCreate(savedInstanceState);
            dm = new DisplayMetrics();

            getWindowManager().getDefaultDisplay().getMetrics(dm);
            gamefont = Typeface.createFromAsset(getAssets(), "Pixel Countdown.otf");
            densityscalefactor = (float)dm.densityDpi / EXPECTED_DENSITY;
            if (densityscalefactor > 1.5f)
                densityscalefactor = 1.5f;
            else if (densityscalefactor < 0.5f)
                densityscalefactor = 0.5f;
            sizescalefactor = (float)dm.widthPixels / EXPECTED_WIDTH;
            if (sizescalefactor > 2f)
                sizescalefactor = 2f;
            else if (sizescalefactor < 0.4f)
                sizescalefactor = 0.4f;
            TS_NORMAL = (int)(40 * sizescalefactor);
            TS_BIG = (int)(60 * sizescalefactor);


            //initialize monster
            Monster monster = MonsterController.getInstance().getLastMetMonster();
            //monster = MonsterController.getInstance().createRandomMonster("Random Monster");
            Monster myMonster = CatchedMonsterController.getInstance().getKeyMonster();
            //temp function for debugging
            if(myMonster == null) {
                myMonster = MonsterController.getInstance().createRandomMonster("Tester");
            }

            // create screens
            playScreen = new PlayScreen(this, monster, myMonster);

            mainView = new FullScreenView(this);
            setContentView(mainView);

            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            soundpool = new SoundPool(15, AudioManager.STREAM_MUSIC, 0);
            soundMap = new HashMap();
            AssetFileDescriptor descriptor = getAssets().openFd("combo2.mp3");
            soundMap.put(Sound.COMBO, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("splat.mp3");
            soundMap.put(Sound.SPLAT, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("wetsplat.mp3");
            soundMap.put(Sound.WETSPLAT, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("wetsplat2.mp3");
            soundMap.put(Sound.KSPLAT, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("whoosh.mp3");
            soundMap.put(Sound.THROW, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("spaz.mp3");
            soundMap.put(Sound.PASSLEVEL, soundpool.load(descriptor, 1));
            descriptor = getAssets().openFd("aww.mp3");
            soundMap.put(Sound.DEATH, soundpool.load(descriptor, 1));
        } catch (Exception e) {
            // panic, crash, fine -- but let me know what happened.
            Log.d(LOG_ID, "onCreate", e);
        }
    }

    BitmapFactory.Options sboptions = new BitmapFactory.Options();
    /**
     * load and scale bitmap according to the apps scale factors.
     * @param fname
     * @return
     */
    public Bitmap getScaledBitmap(String fname) throws IOException
    {
        sboptions.inScreenDensity = dm.densityDpi;
        sboptions.inTargetDensity =  dm.densityDpi;
        sboptions.inDensity = (int)(dm.densityDpi / sizescalefactor); // hack: want to load bitmap scaled for width, abusing density scaling options to do it
        InputStream inputStream = getAssets().open(fname);
        Bitmap btm = BitmapFactory.decodeStream(inputStream, null, sboptions);
        inputStream.close();
        return btm;

//        InputStream inputStream = getAssets().open(fname);
//        Bitmap btm = BitmapFactory.decodeStream(inputStream);
//        inputStream.close();
//        return Bitmap.createScaledBitmap(btm, (int)(btm.getWidth()*sizescalefactor), (int)(btm.getHeight()*sizescalefactor), false);
    }

    public void playSound(Sound s, float vol, float rate) {
        soundpool.play(soundMap.get(s), vol, vol, 0, 0, rate);
    }
    public void playSound(Sound s) {
        playSound(s, 0.9f, 1);
    }

    DisplayMetrics getDisplayMetrics() { return dm; }

    /**
     * Handle resuming of the game,
     */
    @Override
    protected void onResume() {
        super.onResume();
        mainView.resume();
    }

    /**
     * Handle pausing of the game.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mainView.pause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Typeface getGameFont() {
        return gamefont;
    }

    // screen transitions

    /**
     * Start a new game.
     */
    public void startGame() {
        this.playScreen.initGame();
        currentScreen = this.playScreen;
    }



    /**
     * Leave game and return to title screen.
     */
    public void leaveGame() {
        if(playScreen.win) {
            // win 99
            setResult(99);
        } else {
            // loss 100
            setResult(100);
        }
        finish();
    }

    /**
     * completely exit the game.
     */
    public void exit() {
        setResult(100);
        finish();
    }

    /**
     * This inner class handles the main render loop, and delegates drawing and event handling to
     * the individual screens.
     */
    private class FullScreenView extends SurfaceView implements Runnable, View.OnTouchListener {
        private volatile boolean isRendering = false;
        Thread renderThread = null;
        SurfaceHolder holder;

        public FullScreenView(Context context) {
            super(context);
            holder = getHolder();
            currentScreen = playScreen;
            setOnTouchListener(this);
        }

        public void resume() {
            isRendering = true;
            renderThread = new Thread(this);
            renderThread.start();
        }

        @Override
        public void run() {
            try {
                while(isRendering){
                    while(!holder.getSurface().isValid()) {
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) { /* we don't care */  }
                    }

                    // update screen's context
                    currentScreen.update(this);

                    // draw screen
                    Canvas c = holder.lockCanvas();
                    currentScreen.draw(c, this);
                    holder.unlockCanvasAndPost(c);
                }
            } catch (Exception e) {
                // arguably overzealous to grab all exceptions here...but i want to know.
                Log.d(LOG_ID, "View", e);
                e.printStackTrace();
            }
        }

        public void pause() {
            isRendering = false;
            while(true) {
                try {
                    renderThread.join();
                    return;
                } catch (InterruptedException e) {
                    // retry
                }
            }
        }

        public boolean onTouch(View v, MotionEvent event) {
            try {
                return currentScreen.onTouch(event);
            }
            catch (Exception e) {
                // arguably overzealous to grab all exceptions here...but i want to know.
                Log.d(LOG_ID, "onTouch", e);
            }
            return false;
        }
    }
}

