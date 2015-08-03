package com.example.kim.qrmonster.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.controller.CatchedMonsterController;
import com.example.kim.qrmonster.controller.MonsterController;
import com.example.kim.qrmonster.controller.QRcodeController;
import com.example.kim.qrmonster.storage.MonsterStorage;
import com.example.kim.qrmonster.units.Monster;
import com.example.kim.qrmonster.units.QRcode;

import java.util.LinkedList;


public class QRscanResult extends ActionBarActivity {
    private Monster monster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan_result);
        Intent intent = getIntent();
        String contents = intent.getStringExtra(QRscan.SCAN_RESULT);
        //contents = 4294059209454
        QRcode qr = new QRcode();
        qr.set_key(QRcodeController.getInstance().getNextKey());
        qr.set_content(contents);
        qr.set_monsterKey(MonsterController.getInstance().getNextKey());

        if(QRcodeController.getInstance().addQRcode(qr)){
            meetMonster(contents);
        } else {
            //duplicate QR code so output message!
            TextView text = (TextView) findViewById(R.id.title_text);
            text.setText("Duplicate QR code!");
            CardView card = (CardView) findViewById(R.id.card_view);
            card.setVisibility(View.INVISIBLE);
            Log.i("onCreate", "Could not add QR to the list");
        }


    }

    private void meetMonster(String contents) {
        //QR db check for same content
        //if(content == QRlist
        //return display captured monster!
        //else
        monster = new Monster();
        monster = MonsterController.getInstance().createRandomMonster(contents);


        if(MonsterController.getInstance().addMonster(monster)) {
            TextView text = (TextView) findViewById(R.id.qrscan_result);
            text.setTextSize(30);
            text.setText(MonsterController.getInstance().getMonster(monster.get_name()).get_name());
        } else {
            Log.i("meetMonster", "Could not add monster to the list");
        }



    }

    public void catchMonster(View view) {
        if(CatchedMonsterController.getInstance().addMonster(monster)) {
            final LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.monster_name_alert, null);

            new AlertDialog.Builder(this)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //do whatever you want the back key to do
                            Log.i("catchMonster", "Catch Monster Failed!");
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    })
                    .setTitle("You Caught a new QRmonster!")
                    .setIcon(R.drawable.launcher_icon)
                    .setView(linear)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText newName = (EditText)linear.findViewById(R.id.monster_new_name);
                            Monster newMonster = new Monster();
                            newMonster = monster;
                            newMonster.set_name(newName.getText().toString());
                            CatchedMonsterController.getInstance().updateMonster(monster.get_key(), newMonster);
                            Log.i("catchMonster", "Catch Monster!");
                            //setContentView(R.layout.activity_main2);
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }).show();

        } else {
            final LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.monster_name_alert, null);
            EditText editText = (EditText) linear.findViewById(R.id.monster_new_name);
            editText.setVisibility(View.GONE);
            TextView textView = (TextView) linear.findViewById(R.id.monster_instruct_text);
            textView.setText("Failed to Catch this QRmonster!");


            new AlertDialog.Builder(this)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //do whatever you want the back key to do
                            Log.i("catchMonster", "Catch Monster Failed!");
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    })
                    .setTitle("You Failed to Catch this QRmonster")
                    .setIcon(R.drawable.launcher_icon)
                    .setView(linear)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("catchMonster", "Catch Monster Failed!");
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    }).show();
        }
    }

    public void runFromMonster(View view) {
        final LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.monster_name_alert, null);
        EditText editText = (EditText) linear.findViewById(R.id.monster_new_name);
        editText.setVisibility(View.GONE);
        TextView textView = (TextView) linear.findViewById(R.id.monster_instruct_text);
        textView.setText("Ran Away Successfully from QRmonster!");

        new AlertDialog.Builder(this)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //do whatever you want the back key to do
                        Log.i("runFromMonster", "You Ran away from QRmonster!");
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .setTitle("You Ran Away from a QRmonster!")
                .setIcon(R.drawable.launcher_icon)
                .setView(linear)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("runFromMonster", "You Ran away from QRmonster!");
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }).show();
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
