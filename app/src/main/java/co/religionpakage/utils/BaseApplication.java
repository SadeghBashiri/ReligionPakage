package co.religionpakage.utils;

import android.app.Application;
import android.graphics.Typeface;

//import com.orhanobut.hawk.Hawk;
//
//import advance.android.DAGGER.DaggerMyComponent;
//import advance.android.DAGGER.MyComponent;
//import advance.android.DAGGER.MyModule;
//import advance.android.DAGGER2.DaggerMySecondComponent;
//import advance.android.DAGGER2.MySecondComponent;
//import advance.android.DAGGER2.SecondModule;
//import advance.android.FCM.FCMGetToken;
//import advance.android.FCM.FECMGetMessage;
//import io.realm.Realm;

public class BaseApplication extends Application {

    public static BaseApplication app;
    public static Typeface appFace;


//    private static MyComponent myComponent;
//    private static MySecondComponent mySecondComponent;
//
//    public static MyComponent getMyComponent() {
//        return myComponent;
//    }
//
//    public static MySecondComponent getMySecondComponent() {
//        return mySecondComponent;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appFace = Typeface.createFromAsset(getAssets(), "ir_sans.ttf");

//        Realm.init(this);
//        Hawk.init(this).build();
//
//        myComponent = DaggerMyComponent.builder()
//                .myModule(new MyModule(this))
//                .build();
//
//
//        mySecondComponent = DaggerMySecondComponent.builder()
//                .secondModule(new SecondModule(this))
//                .build();
//
//        FCMGetToken fcmGetToken = new FCMGetToken() ;
//        FECMGetMessage getMessage = new FECMGetMessage() ;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
