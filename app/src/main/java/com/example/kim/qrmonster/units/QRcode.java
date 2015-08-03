package com.example.kim.qrmonster.units;

/**
 * Created by kim on 2015-07-30.
 */
public class QRcode {
    private int _key;
    private String _content;
    private int _monsterKey;

    public QRcode() {
        _key = 0;
        _content = "";
        _monsterKey = 0;
    }

    public int get_key() {
        return _key;
    }

    public void set_key(int _key) {
        this._key = _key;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public int get_monsterKey() {
        return _monsterKey;
    }

    public void set_monsterKey(int _monsterKey) {
        this._monsterKey = _monsterKey;
    }
}
