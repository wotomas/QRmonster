package com.example.kim.qrmonster.storage.disk;

/**
 * Created by kim on 2015-08-04.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileManager {
    private static FileManager instance = new FileManager();

    public FileManager(){

    }

    public static FileManager getInstance(){
        return instance;
    }

    public void writeToFile(String s, String fileName, Context context){
        try{
            /** comp 3111 code
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(s);
            writer.close();
             **/
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(s.getBytes());
            fos.close();

            System.out.println("FileManager/writeToFile Saving To Disk at " + fileName);
        }catch(Exception e){
            System.out.println("FileManager/writeToFile Error in Saving to Disk at " + fileName + " With error: " + e.getMessage());
        }
    }

    public String loadFromFile(String fileName, Context context){
        /**
        BufferedReader br = null;
        String result = "";
         **/
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();
            System.out.println("FileManager/loadFromFile Loading from Disk at " + fileName);
            return total.toString();

            /**

            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                result+=sCurrentLine;
            }
             **/
        } catch (IOException e) {
            System.out.println("FileManager/loadFromFile Error in Loading from Disk at " + fileName);

        }
        return "";
    }
}