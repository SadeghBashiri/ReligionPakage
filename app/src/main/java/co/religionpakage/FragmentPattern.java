package co.religionpakage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
public class FragmentPattern extends Fragment {
    DataBaseHandler db;
    TextView errorMassage;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate (R.layout.fragment_pattern_lock_view, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        db = new DataBaseHandler (getActivity (), "YASIN.db", null, 1);
        errorMassage = rootView.findViewById (R.id.textView_errorMassage_pattern);
        final PatternLockView patternLockView  = rootView.findViewById (R.id.patternView);

        patternLockView.addPatternLockListener(new PatternLockViewListener() {
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
                if (PatternLockUtils.patternToString(patternLockView, pattern).equalsIgnoreCase(db.getPasswordApps())) {
                   patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                    Toast.makeText(getActivity (), "خوش آمدید", Toast.LENGTH_LONG).show();

                    Fragment fragment = new FragmentMizkar ();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().show ();

                } else {
                    patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    errorMassage.setText ("رمز اشتباه است");
                   //Toast.makeText(getActivity (), "رمز اشتباه است", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCleared() {
                //Toast.makeText (getActivity (), "onCleared", Toast.LENGTH_LONG).show ();

            }
        });

        return rootView;
    }


}