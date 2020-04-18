package co.religionpakage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

@SuppressWarnings("ALL")
public class FragmentPatternInput1 extends Fragment {
    ImageButton button_ok,button_cancel;
    private DataBaseHandler db;
    String password ;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate (R.layout.fragment_pattern_input_1, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        db = new DataBaseHandler (getActivity (), "YASIN.db", null, 1);
        button_ok = rootView.findViewById (R.id.button_ok);
        button_cancel = rootView.findViewById (R.id.button_cancel);

        button_ok.setVisibility (View.GONE);

        button_ok.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                db.updatePasswordApps (password);
                Toast.makeText (getActivity (), "رمز با موفقعیت ذخیره گردید", Toast.LENGTH_LONG).show ();
                Fragment fragment = new FragmentTanzimat ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().show ();

            }

        });

        button_cancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (getActivity (), "عملیات رمز گذاری کنسل گردید", Toast.LENGTH_LONG).show ();
                Fragment fragment = new FragmentTanzimat ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().show ();
            }
        });

        final PatternLockView patternLockView = rootView.findViewById (R.id.patternView1);

        patternLockView.addPatternLockListener (new PatternLockViewListener () {
            @Override
            public void onStarted() {
                //Toast.makeText (getActivity (), "onStarted", Toast.LENGTH_LONG).show ();
            }

            @Override
            public void onProgress(List progressPattern) {
                // Toast.makeText (getActivity (), "onProgress", Toast.LENGTH_LONG).show ();

            }

            @Override
            public void onComplete(List pattern) {

                button_ok.setVisibility (View.VISIBLE);
                password = PatternLockUtils.patternToString (patternLockView, pattern);
               /* if (PatternLockUtils.patternToString (patternLockView, pattern).equalsIgnoreCase ("123")) {
                    patternLockView.setViewMode (PatternLockView.PatternViewMode.CORRECT);
                    Toast.makeText (getActivity (), "Welcome back, CodingDemos", Toast.LENGTH_LONG).show ();
                } else {
                    patternLockView.setViewMode (PatternLockView.PatternViewMode.WRONG);
                    Toast.makeText (getActivity (), "Incorrect password", Toast.LENGTH_LONG).show ();
                }*/


            }

            @Override
            public void onCleared() {
              //  Toast.makeText (getActivity (), "onCleared", Toast.LENGTH_LONG).show ();

            }
        });

        return rootView;
    }


}