package com.example.kim.qrmonster.units;

import android.graphics.Bitmap;

/**
 * Created by kim on 2015-07-29.
 */
public class Monster {


    //A monster has
    //      KEY     IMG       NAME      ATT    DEF     HLTH    PACE
    //ex)   1      1.png      troll     17     12      120      60

    private int _key;
    private int _image;
    private String _name;
    private int _attack;
    private int _defence;
    private int _health;
    private int _tier;
    private boolean _iskeyMonster;
    private int _experience;
    private int _maxExperience;
    private int _level;

    private int _extraAttack;
    private int _extraDefence;
    private int _extraHealth;

    public int get_extraHealth() {
        return _extraHealth;
    }

    public void set_extraHealth(int _extraHealth) {
        this._extraHealth = _extraHealth;
    }

    public int get_extraDefence() {
        return _extraDefence;
    }

    public void set_extraDefence(int _extraDefence) {
        this._extraDefence = _extraDefence;
    }

    public int get_extraAttack() {
        return _extraAttack;
    }

    public void set_extraAttack(int _extraAttack) {
        this._extraAttack = _extraAttack;
    }

    public Monster() {
        _key = 0;
        _image = 0;
        _name = "";
        _attack = 0;
        _defence = 0;
        _health = 0;
        _tier = 0;
        _iskeyMonster = false;
        _experience = 0;
        _level = 1;
        _extraAttack = 0;
        _extraDefence = 0;
        _extraHealth = 0;

    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public int get_experience() {
        return _experience;
    }

    public void set_experience(int _experience) {
        this._experience = _experience;
    }

    public Monster(String name) {
        _key = 0;
        _image = 0;
        _name = name;
        _attack = 0;
        _defence = 0;
        _health = 0;
        _tier = 0;
        _iskeyMonster = false;
        _experience = 0;
        _level = 1;
        _extraAttack = 0;
        _extraDefence = 0;
        _extraHealth = 0;
    }


    public int get_tier() {
        return _tier;
    }

    public void set_tier(int _tier) {
        this._tier = _tier;
    }

    public int get_key() {
        return _key;
    }

    public void set_key(int _key) {
        this._key = _key;
    }

    public int get_image() {
        return _image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_attack() {
        return _attack;
    }

    public void set_attack(int _attack) {
        this._attack = _attack;
    }

    public int get_defence() {
        return _defence;
    }

    public void set_defence(int _defence) {
        this._defence = _defence;
    }

    public int get_health() {
        return _health;
    }

    public void set_health(int _health) {
        this._health = _health;
    }

    public boolean get_iskeyMonster() {
        return _iskeyMonster;
    }

    public void set_iskeyMonster(boolean _iskeyMonster) {
        this._iskeyMonster = _iskeyMonster;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "_key=" + _key +
                ", _image=" + _image +
                ", _name='" + _name + '\'' +
                ", _attack=" + _attack +
                ", _defence=" + _defence +
                ", _health=" + _health +
                ", _tier=" + _tier +
                ", _iskeyMonster=" + _iskeyMonster +
                ", _experience=" + _experience +
                ", _maxExperience=" + _maxExperience +
                ", _level=" + _level +
                ", _extraAttack=" + _extraAttack +
                ", _extraDefence=" + _extraDefence +
                ", _extraHealth=" + _extraHealth +
                '}';
    }

    public int get_maxExperience() {
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
        if(_level == 1) {
            _maxExperience = 30;
        } else if(_level == 2) {
            _maxExperience = 51;
        } else if(_level == 3) {
            _maxExperience = 86;
        } else if(_level == 4) {
            _maxExperience = 147;
        } else if(_level == 5) {
            _maxExperience = 250;
        } else if(_level == 6) {
            _maxExperience = 423;
        } else if(_level == 7) {
            _maxExperience = 724;
        } else if(_level == 8) {
            _maxExperience = 1231;
        } else if(_level == 9) {
            _maxExperience = 2093;
        } else if(_level == 10) {
            _maxExperience = 3555;
        }
        return _maxExperience;
    }

}
