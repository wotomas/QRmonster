package com.example.kim.qrmonster.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kim.qrmonster.R;


public class QRscan extends ActionBarActivity {

    private TextView formatTxt, contentTxt;
    public final static String SCAN_RESULT = "com.example.kim.qrmonster.activities.QRscan";
    private static final int QRscanResultCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.layout_abs);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout_abs);

        //formatTxt = (TextView)findViewById(R.id.scan_format);
        //contentTxt = (TextView)findViewById(R.id.scan_content);
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrscan, menu);
        return true;
    }
    /**
    public void onScanClick(View view) {
        if(view.getId() == R.id.scan_button){
            //scan
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }
    }
     **/

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

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                        //Log.i("xZing", "contents: " + contents + " format: " + format); // Handle successful scan
                Intent resultIntent = new Intent(this, QRscanResult.class);
                resultIntent.putExtra(SCAN_RESULT, contents );
                startActivityForResult(resultIntent, QRscanResultCode);

            }
            else if(resultCode == RESULT_CANCELED){ // Handle cancel
                Log.i("QRscan", "REturned Back from scan");
                setResult(RESULT_CANCELED);
                finish();
            }
        } else if(requestCode == QRscanResultCode) {
            if(resultCode == RESULT_OK) {
                Intent mainIntent = new Intent();
                setResult(RESULT_OK, mainIntent);
                finish();
            } else if(resultCode == RESULT_CANCELED) {
                Log.i("QRscan", "Ran away from monster");
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }
}
