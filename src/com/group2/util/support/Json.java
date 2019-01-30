package com.group2.util.support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    private Gson json;

    public Json(){
        this.json = new GsonBuilder().serializeNulls().create();
    }

}
