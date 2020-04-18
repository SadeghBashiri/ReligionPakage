package co.religionpakage;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import co.religionpakage.utils.BaseActivity;
import co.religionpakage.utils.Gen;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        TextView version = findViewById (R.id.textView_version);
        // ProgressBar progressBar = findViewById (R.id.progressBar);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String verStr = pInfo.versionName;
            // int verCode = pInfo.versionCode;
            version.setText ("ورژن برنامه"+" "+verStr);
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
            Gen.toast("مشکل در ورژنینگ برنامه!به شرکت ورجاوند اطلاع داده شود");
        }

        //az harjaye application dogme back ra bezanand va peygham khoroj barname zaher shavad ebteda be inja enteghal peyda mikonad
        // vs sepas barname baste mishavad
        if (getIntent ().getBooleanExtra ("EXIT", false)) {
            finish ();
        } else {
            final Handler handler = new Handler ();
            handler.postDelayed (new Runnable () {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    if (db.getId_User () > 0) {
                        Intent intent = new Intent (SplashScreenActivity.this, MainActivity.class);
                        startActivityForResult (intent, 160);
                    } else {
                        Intent intent = new Intent (SplashScreenActivity.this, LoginActivity.class);
                        startActivityForResult (intent, 150);
                    }
                }
            }, 2000);
        }
    }
}
