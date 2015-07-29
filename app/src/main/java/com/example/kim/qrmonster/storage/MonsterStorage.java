package com.example.kim.qrmonster.storage;

/**
 * Created by kim on 2015-07-29.
 */

import android.graphics.Bitmap;

import com.example.kim.qrmonster.units.Monster;
import com.example.kim.qrmonster.controller.MonsterController;

import java.util.LinkedList;

//A monster has
//      KEY     IMG       NAME      ATT    DEF     HLTH    PACE
//ex)   1      1.png      troll     17     12      120      60
public class MonsterStorage {
    private LinkedList<Monster> monsterList;
    private int monsterNumber = 0;


    //initialize storage
    public MonsterStorage()
    {
        monsterList = new LinkedList<Monster>();
    }


    //get monster from list
    public Monster getMonster(String name) {
        for (Monster monster: monsterList) {
            if (monster.get_name().equals(name)) {
                return monster;
            }
        }
        return null;
    }

    //add monster to list
    public boolean addMonster(int key, Bitmap image, String name, int attack, int defence, int health) {
        if (!isDuplicate(key)) {
            //Initialize Monster
            Monster monster = new Monster();
            monster.set_attack(attack);
            monster.set_defence(defence);
            monster.set_health(health);
            monster.set_image(image);
            monster.set_key(key);
            monster.set_name(name);

            //add monster to the list
            monsterList.add(monster);
            monsterNumber++;
            return true;
        } else {
            return false;
        }
    }

    //remove monster from list
    public boolean removeMonster(int key) {
        if(isDuplicate(key)) {
            for(Monster monster: monsterList) {
                if( monster.get_key() == key) {
                    if(monsterList.remove(monster)) {
                        monsterNumber--;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //Update monster from list
    public boolean updateMonster(int key, Monster after) {
        int counter = 0;
        if(isDuplicate(key)) {
            for(Monster monster: monsterList){
                if(monster.get_key() == key){
                    monster.set_name(after.get_name());
                    monster.set_key(after.get_key());
                    monster.set_image(after.get_image());
                    monster.set_health(after.get_health());
                    monster.set_attack(after.get_attack());
                    monster.set_defence(after.get_defence());
                    return true;
                }
            }
        }

        return false;
    }

    public LinkedList<Monster> getMonsterList() {
        return monsterList;
    }


    //helper functions
    private boolean isDuplicate(int key) {
        for(Monster monster: monsterList) {
            if( monster.get_key() == key) {
                return true;
            }
        }
        return false;
    }
}
