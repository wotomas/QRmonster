package com.example.kim.qrmonster.controller;

import com.example.kim.qrmonster.storage.QRcodeStorage;
import com.example.kim.qrmonster.units.QRcode;
import java.util.LinkedList;

/**
 * Created by kim on 2015-07-31.
 */
public class QRcodeController {
    //create static version of QRcodeController
    public static QRcodeController instance = null;
    //create static version of mController
    public static QRcodeStorage mQRcodeStorage = null;

    public QRcodeController() {}

    public static QRcodeController getInstance() {
        if(instance == null) {
            instance = new QRcodeController();
        }
        return instance;
    }

    //Initiate monster storage
    public boolean initQRcodeStorage(QRcodeStorage qrcodeStorage) {
        if(mQRcodeStorage == null) {
            mQRcodeStorage = qrcodeStorage;
            return true;
        }
        return false;
    }

    //get QRcode from storage
    public QRcode getQRcode(int key) {
        return mQRcodeStorage.getQRcode(key);
    }

    //get QRcodeList
    public LinkedList<QRcode> getQRcodeList(){
        return mQRcodeStorage.getQRcodeList();
    }

    //add QRcode to storage
    public boolean addQRcode(QRcode QR) {
        return mQRcodeStorage.addQRcode(QR);
    }

    //remove QRcode from Storage
    public boolean removeQRcode(int key) {
        return mQRcodeStorage.removeQRcode(key);
    }

    //update QRcode from Storage
    public boolean updateQRcode(int key, QRcode after) {
        return mQRcodeStorage.updateQRcode(key, after);
    }

    public int getNextKey() {
        return mQRcodeStorage.getQRcodeNumber();
    }

}
