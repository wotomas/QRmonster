package com.example.kim.qrmonster.controller;

import com.example.kim.qrmonster.storage.CatchedMonsterStorage;
import com.example.kim.qrmonster.storage.MonsterStorage;
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
    public boolean initMonsterStorage(CatchedMonsterStorage catchedMonsterStorage) {
        if(mMonsterStorage == null) {
            mMonsterStorage = catchedMonsterStorage;
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
        if(Math.random() < 0.7) {
            return mMonsterStorage.addMonster(monster);
        }
        return false;
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
