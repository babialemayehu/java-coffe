package com.group2.util.support;

import java.util.Random;

public class Str {
    public static java.lang.String random(int len){
        java.lang.String alpha = "abcdefghijklmnopqrstuvwxyz";
        java.lang.StringBuffer randStr = new StringBuffer();
        Random rand = new Random();
        for(int i = 0;i< len;i++){
            randStr.append(Character.toString(alpha.charAt( rand.nextInt(26))));
        }
        return randStr.toString();
    }
}
