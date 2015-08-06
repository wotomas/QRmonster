package com.example.kim.qrmonster.controller;

/**
 * Created by kim on 2015-07-29.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.kim.qrmonster.storage.QRcodeStorage;
import com.example.kim.qrmonster.storage.disk.JsonStorable;
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
    public boolean initMonsterStorage(MonsterStorage monsterStorage, Context context) {
        if(mMonsterStorage == null) {
            mMonsterStorage = monsterStorage;
            if(mMonsterStorage instanceof JsonStorable) {
                mMonsterStorage = (MonsterStorage) ((JsonStorable)mMonsterStorage).loadFromJson(context);
                if(mMonsterStorage == null) {
                    mMonsterStorage = monsterStorage;
                }
            }
            return true;
        }
        return false;
    }

    public Monster createRandomMonster(String content) {
        int length = content.length();
        //ex) length = 15

        Monster monster = new Monster();
        monster.set_key(MonsterController.getInstance().getNextKey());

        double lowerBound = 0;
        double higherBound = 0;
        if(length < 15){
            lowerBound = 0.6;
            higherBound = 0.95;

            //lowerBound = 0.01;
            //higherBound = 0.02;
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


        monster.set_tier(createMonsterTier(monster));
        Log.d("MonsterController/CreateMonsterTier: Monster Tier is: ", Integer.toString(monster.get_tier()));
        monster.set_name("Random Name " + monster.get_key());
        monster.set_image(createMonsterImage(monster));


        return monster;

    }

    private int createMonsterImage(Monster monster) {
        int tier = monster.get_tier();
        int value = 0;

        if(tier == 1) {
            value = (int)(Math.random() * 2);
        } else if(tier == 2) {
            value = (int)(Math.random() * 3);
        } else if(tier == 3) {
            value = (int)(Math.random() * 4);
        } else if(tier == 4) {
            value = (int)(Math.random() * 6);
        } else if(tier == 5) {
            value = (int)(Math.random() * 3);
        }

        return value;
    }

    public int createMonsterTier(Monster monster) {
        int attack_tier = 0;
        int defence_tier = 0;
        int health_tier = 0;

        int attack = monster.get_attack();
        int defence = monster.get_defence();
        int health = monster.get_health();

        if(attack < 8) {
            attack_tier = 1;
        } else if(attack < 11) {
            attack_tier = 2;
        } else if(attack < 14) {
            attack_tier = 3;
        } else if(attack < 17) {
            attack_tier = 4;
        } else {
            attack_tier = 5;
        }

        if(defence < 3) {
            defence_tier = 1;
        } else if(defence < 5) {
            defence_tier = 2;
        } else if(defence < 7) {
            defence_tier = 3;
        } else if(defence < 9) {
            defence_tier = 4;
        } else {
            defence_tier = 5;
        }

        if(health < 80) {
            health_tier = 1;
        } else if(health < 110) {
            health_tier = 2;
        } else if(health < 140) {
            health_tier = 3;
        } else if(health < 170) {
            health_tier = 4;
        } else {
            health_tier = 5;
        }

        return (int)((attack_tier+defence_tier+health_tier)/3);

    }

    public Monster getMonster(String name) {
        return mMonsterStorage.getMonster(name);
    }

    public LinkedList<Monster> getMonsterList() {
        return mMonsterStorage.getMonsterList();
    }

    public boolean addMonster(Monster monster, Context context){
        return mMonsterStorage.addMonster(monster, context);
    }

    public boolean removeMonster(int key, Context context) {
        return mMonsterStorage.removeMonster(key, context);
    }

    public boolean updateMonster(int key, Monster after, Context context) {
        return mMonsterStorage.updateMonster(key, after, context);
    }


    public int getNextKey() {
        return mMonsterStorage.getMonsterNumber();
    }
}
