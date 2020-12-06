package com.bjtu.redis;

import java.io.*;

//java读json
public class ReadFile {
    public static String readJsonFile(String fileName) {
        StringBuffer them = new StringBuffer();
        int i = 0;
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader jsonReader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");

            while ((i = jsonReader.read()) != -1) {
                them.append((char) i);
            }
            fileReader.close();
            jsonReader.close();

            return them.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
