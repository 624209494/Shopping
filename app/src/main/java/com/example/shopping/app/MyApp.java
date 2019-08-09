package com.example.shopping.app;

import android.app.Application;

public class MyApp  extends Application {
    private static MyApp myApp;
    public static boolean  IMG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }
    public static MyApp  getInstance(){
        return myApp;
    }
}
