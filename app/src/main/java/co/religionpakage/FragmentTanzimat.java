package co.religionpakage;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Field;

import co.religionpakage.utils.Gen;


public class FragmentTanzimat extends Fragment {
    private RelativeLayout pattern,relativeLayout_afterTime,numeric;
    private LinearLayout linearLayout_choicePassword;
    private Switch switch_lock;
    Spinner spinner_time;
    /*private RelativeLayout layout_Kartabl;*/
    View rootView;
    private String[] time = {"0.5", "1", "1.5", "2", "3","4","5"};
    private DataBaseHandler db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_tanzimat, container, false);
        db = new DataBaseHandler (getActivity (), "YASIN.db", null, 1);
        bind_Layout ();


        pattern.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                bind_Layout ();
               // Toast.makeText (getActivity (), spinner_time.getSelectedItem().toString()+"", Toast.LENGTH_LONG).show ();

                db.insertPasswordApps("pattern",spinner_time.getSelectedItem().toString()+"");
                Fragment fragment = new FragmentPatternInput1 ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });

        numeric.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                bind_Layout ();
                db.insertPasswordApps("numeric",spinner_time.getSelectedItem().toString()+"");
                Gen.log("تنظیمات");
               // Toast.makeText (getActivity (),"فعلا تنظیم نشده", Toast.LENGTH_LONG).show ();
                Fragment fragment = new FragmentNumericInput ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

        });

        switch_lock.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                db.deleteTanzimat();
                bind_Layout();

            }
        });
        /*PersianDateMe dateNow = new PersianDateMe ();
        FormatPersianDate pdformater1 = new FormatPersianDate ("Y/m/d");

        txtTaghvim.setText (" امروز " + dateNow.dayName () + " مورخ " + pdformater1.format (dateNow));

        layout_Kartabl.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (getActivity (), "Amaliyat move", Toast.LENGTH_LONG).show ();
            }
        });*/

        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //Toast.makeText (getActivity (), time[position], Toast.LENGTH_LONG).show ();
                db.updateTimerLockApps(time[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return rootView;
    }

    private void bind_Layout() {

        pattern = rootView.findViewById (R.id.relativeLayout_pattern);
        numeric = rootView.findViewById (R.id.relativeLayout_numeric);
        switch_lock = rootView.findViewById (R.id.switch_lock);
        relativeLayout_afterTime = rootView.findViewById (R.id.relativeLayout_afterTime);
        linearLayout_choicePassword = rootView.findViewById (R.id.linearLayout_choicePassword);

        spinner_time =rootView.findViewById (R.id.spinner_time);
        try {
            ArrayAdapter<String> array_time = new ArrayAdapter<>(getActivity (), android.R.layout.simple_spinner_item, time);
            array_time.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
            spinner_time.setAdapter (array_time);
            spinner_time.setSelection (db.getTimerLockApps());

            Field popup = Spinner.class.getDeclaredField ("mPopup");
            popup.setAccessible (true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get (spinner_time);
            popupWindow.setHeight (500);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            Toast.makeText (getActivity (),"پیغام خطای اسپینر زمان قفل شدن", Toast.LENGTH_LONG).show ();
        }

        if(db.checkLockAppsComplete()){
            switch_lock.setChecked (true);
        }
       /* else {
            //spinner_time.setSelection (0);
        }*/

        boolean switchState = switch_lock.isChecked();
        if (switchState){
            relativeLayout_afterTime.setVisibility (View.VISIBLE);
            linearLayout_choicePassword.setVisibility (View.VISIBLE);
        }
        else {
            relativeLayout_afterTime.setVisibility (View.GONE);
            linearLayout_choicePassword.setVisibility (View.GONE);
            db.deleteTanzimat();
        }
    }

    @Override
    public void onResume() {
        bind_Layout ();
        super.onResume();
    }

    @Override
    public void onPause() {
        bind_Layout ();
       // Toast.makeText (getActivity (),"onPause", Toast.LENGTH_LONG).show ();
        super.onPause();
    }

    @Override
    public void onStop() {
        bind_Layout ();
        super.onStop();
    }
}