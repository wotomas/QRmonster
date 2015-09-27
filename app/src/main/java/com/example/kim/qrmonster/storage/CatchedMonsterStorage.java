package com.example.kim.qrmonster.storage;

import android.content.Context;
import android.util.Log;

import com.example.kim.qrmonster.storage.disk.FileManager;
import com.example.kim.qrmonster.storage.disk.JsonStorable;
import com.example.kim.qrmonster.units.Monster;
import com.google.gson.Gson;

import java.util.LinkedList;

/**
 * Created by kim on 2015-07-31.
 */
public class CatchedMonsterStorage implements JsonStorable {
    private LinkedList<Monster> monsterList;
    private int monsterNumber = 0;
    private int keyMonster = 0;

    //initialize storage
    public CatchedMonsterStorage()
    {
        monsterList = new LinkedList<Monster>();
    }


    //set key monster
    public boolean setKeyMonster(Monster monster) {
        for (Monster m: monsterList) {
            if (m == monster) {
                System.out.println("CatchMonsterStorage/setKeyMonster: Key Monster is set to: " + monster.get_name());
                keyMonster = monster.get_key();
                monster.set_iskeyMonster(true);
                return true;
            }
        }
        System.out.println("CatchMonsterStorage/setKeyMonster: No key Monster for :" + monster.get_name());
        return false;
    }

    //get key monster
    public int getKeyMonster() {
        for (Monster monster: monsterList) {
                if(monster.get_iskeyMonster())
                {
                    System.out.println("CatchMonsterStorage/getKeyMonster: Key Monster is set to: " + monster.get_name());
                    //monster.set_iskeyMonster(true);
                    return monster.get_key();
                }
        }
        System.out.println("CatchMonsterStorage/getKeyMonster: Failed to get key monster");
        return -1;
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

    //get monster from list by key
    public Monster getMonster(int key) {
        for (Monster monster: monsterList) {
            if (monster.get_key() == key) {
                return monster;
            }
        }
        return null;
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

    public boolean isKeyMonster(Monster monster) {
        if(monster.get_key() == keyMonster) {
            return true;
        } else {
            return false;
        }
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

    public boolean gainExperience(Monster monster) {
        monster.set_experience(monster.get_experience() + 10);
        if(monster.get_experience() > 3555) {
            return false;
        } else {
            checkLevelUp(monster);
            return true;
        }
    }

    private void checkLevelUp(Monster monster) {
        int previousLevel = monster.get_level();
        //level 1 - 30
        //level 2 - 51;
        //level 3 - 86;
        //level 4 - 147;
        //level 5 - 250
        //level 6 - 423
        //level 7 - 724
        //level 8 - 1231
        //level 9 - 2093
        //level 10 - 3555

        if(monster.get_experience() > 3555) {
            monster.set_level(11);
        } else if(monster.get_experience() > 2093) {
            monster.set_level(10);
        } else if(monster.get_experience() > 1231) {
            monster.set_level(9);
        } else if(monster.get_experience() > 724) {
            monster.set_level(8);
        }else if(monster.get_experience() > 423) {
            monster.set_level(7);
        } else if(monster.get_experience() > 250) {
            monster.set_level(6);
        } else if(monster.get_experience() > 147) {
            monster.set_level(5);
        } else if(monster.get_experience() > 86) {
            monster.set_level(4);
        } else if(monster.get_experience() > 51) {
            monster.set_level(3);
        } else if(monster.get_experience() > 30) {
            monster.set_level(2);
        } else {
            monster.set_level(1);
        }

        if(previousLevel < monster.get_level()) {
            //monster levved up
            Log.d("level up", "Monster leved UP");
            int decider = (int)(Math.random() * 3);
            if(decider == 0) {
                monster.set_extraAttack(monster.get_extraAttack() + (int)(Math.random() * 5));
            } else if(decider == 1) {
                monster.set_extraDefence(monster.get_extraDefence() + (int) (Math.random() * 2));
            } else if(decider == 2) {
                monster.set_extraHealth(monster.get_extraHealth() + 5 * (int)(Math.random() * 6));
            }
        }
    }

    //Disk Storage
    public String getFileName() {
        return "CATCHED_MONSTER.txt";
    }

    @Override
    public Object loadFromJson(Context context){
        System.out.println("MonsterStorage/loadFromJson Saving To Disk at " + context.getFilesDir().toString());
        Gson gson = new Gson();
        String json = FileManager.getInstance().loadFromFile(getFileName(), context);
        if (json.equals("")) return null;
        return gson.fromJson(json, CatchedMonsterStorage.class);
    }

    @Override
    public void saveToJson(Context context) {
        Gson gson = new Gson();
        FileManager.getInstance().writeToFile(gson.toJson(this), getFileName(), context);
    }



}
