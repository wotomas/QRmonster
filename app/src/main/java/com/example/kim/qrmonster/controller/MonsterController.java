package com.example.kim.qrmonster.controller;

/**
 * Created by kim on 2015-07-29.
 */

import com.example.kim.qrmonster.units.Monster;
import com.example.kim.qrmonster.storage.MonsterStorage;
import java.util.ArrayList;

public class MonsterController {
    private static MonsterController instance = null;
    private static int monsterCount = 0;
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



}
