package com.example.kim.qrmonster.storage.disk;

import android.content.Context;

/**
 * Created by kim on 2015-08-04.
 */

public interface JsonStorable {

    public String getFileName();

    public Object loadFromJson(Context context);

    public void saveToJson(Context context);
}
