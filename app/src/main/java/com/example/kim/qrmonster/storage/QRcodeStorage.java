package com.example.kim.qrmonster.storage;

/**
 * Created by kim on 2015-07-30.
 */
import com.example.kim.qrmonster.units.QRcode;

import java.util.LinkedList;

public class QRcodeStorage {
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
    public boolean addQRcode(QRcode QR) {
        if(!isDuplicate(QR.get_content())) {
            QRcodeList.add(QR);
            QRcodeNumber++;
            return true;
        }
        return false;
    }

    //remove QR code from list
    public boolean removeQRcode(int key) {
        if(isDuplicate(key)){
            for(QRcode QR: QRcodeList) {
                if( QR.get_key() == key) {
                    if(QRcodeList.remove(QR)) {
                        QRcodeNumber--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //update QRcode from list
    public boolean updateQRcode(int key, QRcode after) {
        if(isDuplicate(key)){
            for(QRcode QR: QRcodeList) {
                if(QR.get_key() == key){
                    QR.set_key(after.get_key());
                    QR.set_content(after.get_content());
                    QR.set_monsterKey(after.get_monsterKey());
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



}
