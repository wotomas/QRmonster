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

    public Monster createRandomMonster(String content) {
        int length = content.length();
        //ex) length = 15

        Monster monster = new Monster();
        monster.set_key(MonsterController.getInstance().getNextKey());
        monster.set_name("Random Name " + monster.get_key());
        monster.set_image(null);
        double lowerBound = 0;
        double higherBound = 0;
        if(length < 15){
            lowerBound = 0.6;
            higherBound = 0.95;
        } else {
            lowerBound = 0.5;
            higherBound = 0.90;
        }

        double random = Math.random();
        if(random < lowerBound) {
                monster.set_attack(5 + (int)(Math.random() * 3));
                monster.set_defence(2 + (int) (Math.random() * 2));
                monster.set_health(50 + (int) (Math.random() * 30));
        } else if (random >= lowerBound && random < higherBound) {
            monster.set_attack(7 + (int)(Math.random() * 5));
            monster.set_defence(3 + (int) (Math.random() * 3));
            monster.set_health(70 + (int) (Math.random() * 50));
        } else {
            monster.set_attack(10 + (int)(Math.random() * 10));
            monster.set_defence(5 + (int) (Math.random() * 5));
            monster.set_health(90 + (int) (Math.random() * 100));
        }

        return monster;

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
