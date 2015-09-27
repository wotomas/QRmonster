package com.example.kim.qrmonster.storage;

/**
 * Created by kim on 2015-07-29.
 */

import android.content.Context;
import android.graphics.Bitmap;

import com.example.kim.qrmonster.storage.disk.FileManager;
import com.example.kim.qrmonster.storage.disk.JsonStorable;
import com.example.kim.qrmonster.units.Monster;
import com.example.kim.qrmonster.controller.MonsterController;
import com.google.gson.Gson;

import java.util.LinkedList;

//A monster has
//      KEY     IMG       NAME      ATT    DEF     HLTH    PACE
//ex)   1      1.png      troll     17     12      120      60
public class MonsterStorage implements JsonStorable {
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

    public Monster getLastMetMonster() {
        return monsterList.getLast();
    }

    public int getMonsterNumber() {
        return monsterNumber;
    }

    //add monster to list
    public boolean addMonster(Monster monster, Context context) {
        if (!isDuplicate(monster.get_key())) {
            monsterList.add(monster);
            monsterNumber++;
            saveToJson(context);
            return true;
        }
        return false;
    }

    //remove monster from list
    public boolean removeMonster(int key, Context context) {
        if(isDuplicate(key)) {
            for(Monster monster: monsterList) {
                if( monster.get_key() == key) {
                    if(monsterList.remove(monster)) {
                        monsterNumber--;
                        saveToJson(context);
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    //Update monster from list
    public boolean updateMonster(int key, Monster after, Context context) {
        if(isDuplicate(key)) {
            for(Monster monster: monsterList){
                if(monster.get_key() == key){
                    monster.set_name(after.get_name());
                    monster.set_key(after.get_key());
                    monster.set_image(after.get_image());
                    monster.set_health(after.get_health());
                    monster.set_attack(after.get_attack());
                    monster.set_defence(after.get_defence());
                    monster.set_level(after.get_level());
                    monster.set_experience(after.get_experience());
                    saveToJson(context);
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

    //Disk Storage
    public String getFileName() {
        return "MONSTER.txt";
    }

    @Override
    public Object loadFromJson(Context context){
        System.out.println("MonsterStorage/loadFromJson Saving To Disk at " + context.getFilesDir().toString());
        Gson gson = new Gson();
        String json = FileManager.getInstance().loadFromFile(getFileName(), context);
        if (json.equals("")) return null;
        return gson.fromJson(json, MonsterStorage.class);
    }

    @Override
    public void saveToJson(Context context) {
        Gson gson = new Gson();
        FileManager.getInstance().writeToFile(gson.toJson(this), getFileName(), context);
    }

}
