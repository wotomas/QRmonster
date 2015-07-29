package com.example.kim.qrmonster.controller;

/**
 * Created by kim on 2015-07-29.
 */

import android.graphics.Bitmap;

import com.example.kim.qrmonster.units.Monster;
import com.example.kim.qrmonster.storage.MonsterStorage;
import java.util.ArrayList;
import java.util.LinkedList;

public class MonsterController {
    private static MonsterController instance = null;
    private static MonsterStorage mMonsterStorage = null;

    public MonsterController() {

    }

    public static MonsterController getInstance() {
        if(instance == null) {
            instance = new MonsterController();
        }
        return instance;
    }

    //Initiate monster storage
    public boolean initMonsterStorage(MonsterStorage monsterStorage) {
        if(mMonsterStorage == null) {
            mMonsterStorage = monsterStorage;
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

    public boolean addMonster(Monster monster){
        return mMonsterStorage.addMonster(monster);
    }

    public boolean removeMonster(int key) {
        return mMonsterStorage.removeMonster(key);
    }

    public boolean updateMonster(int key, Monster after) {
        return mMonsterStorage.updateMonster(key, after);
    }


    public int getNextKey() {
        return mMonsterStorage.getMonsterNumber();
    }
}
