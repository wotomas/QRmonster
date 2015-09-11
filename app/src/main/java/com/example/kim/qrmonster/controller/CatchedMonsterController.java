package com.example.kim.qrmonster.controller;

import android.content.Context;

import com.example.kim.qrmonster.activities.Main;
import com.example.kim.qrmonster.storage.CatchedMonsterStorage;
import com.example.kim.qrmonster.storage.MonsterStorage;
import com.example.kim.qrmonster.storage.disk.JsonStorable;
import com.example.kim.qrmonster.units.Monster;

import java.util.LinkedList;

/**
 * Created by kim on 2015-07-31.
 */
public class CatchedMonsterController {
    private static CatchedMonsterController instance = null;
    private static CatchedMonsterStorage mMonsterStorage = null;

    public CatchedMonsterController() {

    }

    public static CatchedMonsterController getInstance() {
        if(instance == null) {
            instance = new CatchedMonsterController();
        }
        return instance;
    }

    //Initiate monster storage
    public boolean initMonsterStorage(CatchedMonsterStorage catchedMonsterStorage, Context context) {
        if(mMonsterStorage == null) {
            mMonsterStorage = catchedMonsterStorage;
            if(mMonsterStorage instanceof JsonStorable) {
                mMonsterStorage = (CatchedMonsterStorage) (((JsonStorable) mMonsterStorage).loadFromJson(context));
                if(mMonsterStorage == null) {
                    mMonsterStorage = catchedMonsterStorage;
                }
            }
            return true;
        }
        return false;
    }

    public Monster getMonster(String name) {
        return mMonsterStorage.getMonster(name);
    }

    public LinkedList<Monster> getMonsterList() {
        return mMonsterStorage.getMonsterList();
    }

    public boolean addMonster(Monster monster,  Context context){
        if(Math.random() < 0.9) {
            return mMonsterStorage.addMonster(monster, context);
        }
        return false;
    }

    public boolean removeMonster(int key,  Context context) {
        return mMonsterStorage.removeMonster(key, context);
    }

    public boolean updateMonster(int key, Monster after,  Context context) {
        return mMonsterStorage.updateMonster(key, after, context);
    }


    public int getNextKey() {
        return mMonsterStorage.getMonsterNumber();
    }

    public boolean isKeyMonster(Monster monster){
        return mMonsterStorage.isKeyMonster(monster);
    }

    public Monster getKeyMonster() {
        return mMonsterStorage.getMonster(mMonsterStorage.getKeyMonster());
    }

    public boolean setKeyMonster(Monster monster) {
        if(mMonsterStorage.setKeyMonster(monster)) {
            return true;
        } else {
            return false;
        }

    }
}
