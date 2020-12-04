package com.bjtu.redis;

import java.io.*;

public class ReadFile {
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            int i = 0;
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader jsonReader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            StringBuffer them = new StringBuffer();
            while ((i = jsonReader.read()) != -1) {
                them.append((char) i);
            }
            fileReader.close();
            jsonReader.close();
            jsonStr = them.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
