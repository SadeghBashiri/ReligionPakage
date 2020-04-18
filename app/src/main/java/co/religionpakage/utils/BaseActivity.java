package co.religionpakage.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import co.religionpakage.DataBaseHandler;
import co.religionpakage.SplashScreenActivity;

public class BaseActivity extends AppCompatActivity {

    public Context mContext = this;
    public Activity mActivity = this;

    public String TAG = getClass().getSimpleName()+"_debug";

    public ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        dialog.setTitle("loading");
        dialog.setMessage("please wait to load");
    }

    public DataBaseHandler db = new DataBaseHandler(this, "YASIN.db", null, 1);

    @Override
    public void onBackPressed() {
        // MyToast ("click back");
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder (this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder (this);
        }
        builder.setTitle ("خروج")
                .setMessage ("آیا مایل به بستن برنامه هستید؟")
                .setPositiveButton ("بله", new DialogInterface.OnClickListener () {
                    public void onClick(DialogInterface dialog, int which) {
                        // close activity
                        Gen.toast ("تا درودی دیگر, بدرود...");
                        // finish ();
                        Intent intent = new Intent (getApplicationContext (), SplashScreenActivity.class);
                        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra ("EXIT", true);
                        startActivity (intent);
                    }
                })
                .setNegativeButton ("خیر", new DialogInterface.OnClickListener () {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon (android.R.drawable.ic_dialog_alert)
                .show ();
    }
}
