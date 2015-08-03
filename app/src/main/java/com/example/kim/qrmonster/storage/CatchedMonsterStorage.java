package com.example.kim.qrmonster.storage;

import com.example.kim.qrmonster.units.Monster;

import java.util.LinkedList;

/**
 * Created by kim on 2015-07-31.
 */
public class CatchedMonsterStorage {
    private LinkedList<Monster> monsterList;
    private int monsterNumber = 0;


    //initialize storage
    public CatchedMonsterStorage()
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

    public int getMonsterNumber() {
        return monsterNumber;
    }

    //add monster to list
    public boolean addMonster(Monster monster) {
        if (!isDuplicate(monster.get_key())) {
            monsterList.add(monster);
            monsterNumber++;
            return true;
        }
        return false;
    }

    //remove monster from list
    public boolean removeMonster(int key) {
        if(isDuplicate(key)) {
            for(Monster monster: monsterList) {
                if( monster.get_key() == key) {
                    if(monsterList.remove(monster)) {
                        monsterNumber--;
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    //Update monster from list
    public boolean updateMonster(int key, Monster after) {
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