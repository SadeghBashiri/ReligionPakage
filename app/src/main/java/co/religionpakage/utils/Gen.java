package co.religionpakage.utils;

import android.util.Log;
import android.widget.Toast;

//import advance.android.BuildConfig;
//import advance.android.PHP_REST.PHPEndPoints;
//import advance.android.PHP_REST.PHPGenerator;
//import advance.android.Retrofit.EndPoints;
//import advance.android.Retrofit.Generator;
import co.religionpakage.BuildConfig;

public class Gen {

//    public static String YAhooBaseURL = "https://query.yahooapis.com/v1/public/" ;
//    public static String IP_API = "http://ip-api.com/" ;
//
//    public static EndPoints endPoints =
//            Generator.createService(EndPoints.class) ;
//
//public static PHPEndPoints phpEndPoints =
//        PHPGenerator.createService(PHPEndPoints.class) ;



    public static void toast(String msg) {
        Toast.makeText(BaseApplication.app, msg, Toast.LENGTH_SHORT).show();
    }

    public static void log(String tag, String msg) {
        if (BuildConfig.DEBUG)
            Log.d(tag, msg);
    }

    public static void log(String msg) {
        if (BuildConfig.DEBUG)
            Log.d("debug_log", msg);
    }


    public static int plus(int a , int b ){
        return a  + b ;
    }


}
