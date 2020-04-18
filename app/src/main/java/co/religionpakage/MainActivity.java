package co.religionpakage;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;

import co.religionpakage.customViews.MyTextView;
import co.religionpakage.utils.BaseActivity;
import co.religionpakage.utils.Gen;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private TextView toolbar_title;
    private int idCurrent;
    private NavigationView navigationView;
    private TextView txt_test;
    private MyTextView txt_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

    }

    private void bind() {
        Toolbar toolbar = findViewById(R.id.toolbar_mainActivity);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);
        navigationView.getMenu().getItem(0).setChecked(true);
//
//        ImageButton_refresh = findViewById (R.id.imgButton_refresh);
//        ImgButton_filter = findViewById (R.id.imgButton_filter);
//
//        tabLayout = findViewById (R.id.tabs);
//        viewPager = findViewById (R.id.viewPager_summery);
//        linearLayout_choiceYear = findViewById (R.id.linearLayout_choiceYear);
//        linearLayout_choiceDate = findViewById (R.id.linearLayout_choiceDate);
        toolbar_title = findViewById (R.id.toolbar_title);
//        tarikhErjaAz = findViewById (R.id.textView_tarikhErjaAz);
//        tarikhErjaTa = findViewById (R.id.textView_tarikhErjaTa);
//
//        Spinner spinner = findViewById(R.id.spinner_year);
//        spinner.setOnItemSelectedListener (this);
//        try {
//            ArrayAdapter<String> aa = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, year);
//            aa.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
//
//            spinner.setAdapter (aa);
//            spinner.setSelection (2);
//
//
//            Field popup = Spinner.class.getDeclaredField ("mPopup");
//            popup.setAccessible (true);
//
//            // Get private mPopup member variable and try cast to ListPopupWindow
//            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get (spinner);
//
//            popupWindow.setHeight (300);
//        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
//            // silently fail...
//            Gen.toast("پیغام خطای اسپینر");
//        }

    }

    private void dispalayFragment(int id) {

        idCurrent = id;
        Fragment fragment = null;

//         if (id == R.id.nav_kartabl || id == R.id.nav_peygiri) {
//
//            ImgButton_filter.setVisibility (View.VISIBLE);
//            linearLayout_choiceDate.setVisibility (View.VISIBLE);
//        } else {
//            ImgButton_filter.setVisibility (View.GONE);
//            linearLayout_choiceDate.setVisibility (View.GONE);
//        }
//
//        if (id == R.id.nav_tanzimat) {
//            ImageButton_refresh.setVisibility (View.GONE);
//        } else {
//            ImageButton_refresh.setVisibility (View.VISIBLE);
//        }

        switch (id) {
//        case R.id.nav_kartabl:
//            fragment = new FragmentKartabl ();
//            toolbar_title.setText (getString (R.string.kartabl));
//            PersianDateMe dateNow = new PersianDateMe ();
//            FormatPersianDate pdformater1 = new FormatPersianDate ("Y/m/d");
//            if ((tarikhErjaAz.getText ().toString () + "").equals (getString (R.string.tarikh_erja_az))) {
//                tarikhErjaAz.setText ("از " + pdformater1.format (dateNow));
//                tarikhErjaTa.setText ("تا " + pdformater1.format (dateNow));
//            }
//            break;
            case R.id.nav_peygiri:
                toolbar_title.setText("صلوات شمار");
                fragment = new FragmentSalavat();
                break;
            case R.id.nav_tanzimat:
                toolbar_title.setText("تنظیمات");
                fragment = new FragmentTanzimat();
                break;
            case R.id.nav_taghirKarbar:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 170);
                db.updateLastLogin();
                break;
            case R.id.nav_khoroj:
                onBackPressed();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        dispalayFragment(menuItem.getItemId());
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPause() {
        // MyToast ("onPause Run");
        db.updateTimerPauseApps();
        super.onPause();
    }

    @Override
    public void onResume() {
        db.checkLockAppsComplete();
        if (!db.checkLockApps()) {
            // MyToast ("onResume Run");
            if (db.getModelLockerApps().equals("pattern")) {
                Fragment fragment = new FragmentPattern();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                // fragmentTransaction.addToBackStack (null);
                fragmentTransaction.commit();
                navigationView.getMenu().getItem(0).setChecked(true);
            } else {
                Fragment fragment = new FragmentNumeric();
                FragmentManager fragmentManager = this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                // fragmentTransaction.addToBackStack (null);
                fragmentTransaction.commit();
            }
        }


        super.onResume();
    }

//    private void setMenuCounter(@IdRes int itemId, int count) {
//
//        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
//        view.setText(count > 0 ? String.valueOf(count) : null);
//
//    }
}
