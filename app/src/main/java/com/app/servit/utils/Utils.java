package com.app.servit.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Toast;

import com.app.servit.R;

public class Utils {
    public static String getUrlBase(){
        return "http://161.35.220.138:6000/";
    }

    public static void enviarToast(String msg, Context context){
        //Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = null;
        toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

    public static void enviarToastLong(String msg, Context context){
        int duration = Toast.LENGTH_LONG;
        Toast toast = null;
        toast = Toast.makeText(context, msg, duration);
        toast.show();
    }
}
