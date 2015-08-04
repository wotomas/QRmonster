package com.example.kim.qrmonster.storage;

/**
 * Created by kim on 2015-07-30.
 */
import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.kim.qrmonster.storage.disk.FileManager;
import com.example.kim.qrmonster.storage.disk.JsonStorable;
import com.example.kim.qrmonster.units.QRcode;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.jar.JarEntry;

public class QRcodeStorage implements JsonStorable {
    private LinkedList<QRcode> QRcodeList;
    private int QRcodeNumber;


    //initialize
    public QRcodeStorage() {
        QRcodeList = new LinkedList<QRcode>();
    }

    //get QRcodeList
    public LinkedList<QRcode> getQRcodeList() {
        return QRcodeList;
    }

    //get QRcode from list
    public QRcode getQRcode(int key) {
        for(QRcode QR: QRcodeList) {
            if(QR.get_key() == key) {
                return QR;
            }
        }
        return null;
    }

    //get total Number of QR codes
    public int getQRcodeNumber() {
        return QRcodeNumber;
    }

    //add QR code to list
    public boolean addQRcode(QRcode QR, Context context) {
        if(!isDuplicate(QR.get_content())) {
            QRcodeList.add(QR);
            QRcodeNumber++;
            saveToJson(context);
            return true;
        }
        return false;
    }

    //remove QR code from list
    public boolean removeQRcode(int key, Context context) {
        if(isDuplicate(key)){
            for(QRcode QR: QRcodeList) {
                if( QR.get_key() == key) {
                    if(QRcodeList.remove(QR)) {
                        QRcodeNumber--;
                        saveToJson(context);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //update QRcode from list
    public boolean updateQRcode(int key, QRcode after, Context context) {
        if(isDuplicate(key)){
            for(QRcode QR: QRcodeList) {
                if(QR.get_key() == key){
                    QR.set_key(after.get_key());
                    QR.set_content(after.get_content());
                    QR.set_monsterKey(after.get_monsterKey());
                    saveToJson(context);
                    return true;
                }
            }
        }
        return false;
    }

    //helper functions
    private boolean isDuplicate(String content) {
        for(QRcode QR: QRcodeList) {
            if( QR.get_content().equals(content)) {
                return true;
            }
        }
        return false;
    }

    //helper functions
    private boolean isDuplicate(int key) {
        for(QRcode QR: QRcodeList) {
            if( QR.get_key() == key) {
                return true;
            }
        }
        return false;
    }

    //Disk Storage
   public String getFileName() {
       return "QR_CODE.txt";
   }

    @Override
    public Object loadFromJson(Context context){
        System.out.println("QRcodeStorage/loadFromJson Saving To Disk at " + context.getFilesDir().toString());
        Gson gson = new Gson();
        String json = FileManager.getInstance().loadFromFile(getFileName(), context);
        if (json.equals("")) return null;
        return gson.fromJson(json, QRcodeStorage.class);
    }

    @Override
    public void saveToJson(Context context) {
        Gson gson = new Gson();
        FileManager.getInstance().writeToFile(gson.toJson(this), getFileName(), context);
    }




}
