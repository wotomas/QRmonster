package com.example.kim.qrmonster.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.controller.MonsterController;
import com.example.kim.qrmonster.storage.MonsterStorage;
import com.example.kim.qrmonster.units.Monster;

import java.util.LinkedList;


public class QRscanResult extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan_result);
        Intent intent = getIntent();
        String contents = intent.getStringExtra(QRscan.SCAN_RESULT);
        //contents = 4294059209454
        meetMonster(contents);

    }

    private void meetMonster(String contents) {
        //QR db check for same content
        //if(content == QRlist
        //return display captured monster!
        //else
        Monster monster = new Monster();

        monster.set_key(MonsterController.getInstance().getNextKey());
        monster.set_name("Random Name " + monster.get_key());
        monster.set_image(null);
        monster.set_attack((int)(Math.random() * 10));
        monster.set_defence((int) (Math.random() * 5));
        monster.set_health((int) (Math.random() * 100));

        if(MonsterController.getInstance().addMonster(monster)) {
            TextView text = (TextView) findViewById(R.id.qrscan_result);
            text.setTextSize(30);
            text.setText(MonsterController.getInstance().getMonster(monster.get_name()).get_name());
        } else {
            Log.i("meetMonster", "Could not add monster to the list");
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrscan_result, menu);
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
}
