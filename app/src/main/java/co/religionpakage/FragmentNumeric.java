package co.religionpakage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentNumeric extends Fragment {
    DataBaseHandler db;
    private TextView errorMassage;
    private EditText password;
    View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate (R.layout.fragment_numeric_lock_view, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        db = new DataBaseHandler (getActivity (), "YASIN.db", null, 1);
        errorMassage = rootView.findViewById (R.id.textView_errorMassage);
        Button button = rootView.findViewById (R.id.button_logIn);
        password = rootView.findViewById (R.id.editText_Inputpassword);



        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if ((password.getText ().toString () + "").equals (db.getPasswordApps ())) {
                    Toast.makeText (getActivity (), "خوش آمدید", Toast.LENGTH_LONG).show ();
                    Fragment fragment = new FragmentMizkar ();
                    FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.replace (R.id.frameLayout, fragment);
                    fragmentTransaction.addToBackStack (null);
                    fragmentTransaction.commit ();
                    ((AppCompatActivity) getActivity ()).getSupportActionBar ().show ();

                } else {
                    errorMassage.setText ("رمز اشتباه است");
                    password.setError ("");
                }

            }
        });

        return rootView;
    }


}
