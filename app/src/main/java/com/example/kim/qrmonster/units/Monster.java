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

    public Monster() {
        _key = 0;
        _image = 0;
        _name = "";
        _attack = 0;
        _defence = 0;
        _health = 0;
        _tier = 0;
        _iskeyMonster = false;
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
                '}';
    }
}
