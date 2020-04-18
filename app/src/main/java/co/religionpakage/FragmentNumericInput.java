package co.religionpakage;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.religionpakage.utils.Gen;


public class FragmentNumericInput extends Fragment {
    private EditText password;
    private ImageButton button_ok, button_cancel;
    private DataBaseHandler db;
    View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_numeric_input, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        db = new DataBaseHandler (getActivity (), "YASIN.db", null, 1);
        password = rootView.findViewById (R.id.editText_password);
        button_ok = rootView.findViewById (R.id.button_ok);
        button_cancel = rootView.findViewById (R.id.button_cancel);


        button_ok.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                db.updatePasswordApps (password.getText ().toString ()+"");
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
        return rootView;
    }


}
