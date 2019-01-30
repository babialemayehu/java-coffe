package com.group2.util.database;

public enum Where {
    EQUAL("="), GREATERTHAN(">"), LESSTHAN("<");

    String opperation;
    Where(String opperation){
        this.opperation = opperation;
    }

    public String getOpperation(){
        return this.opperation;
    }
}
