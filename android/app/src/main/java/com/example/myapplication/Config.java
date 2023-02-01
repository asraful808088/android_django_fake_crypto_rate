package com.example.myapplication;

public class Config {
    private static String websocketURI="ws://192.168.0.104:8000/ws/crypto";
    public static String getWsURI(){
        return websocketURI;
    }
}
