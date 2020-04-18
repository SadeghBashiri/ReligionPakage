package co.religionpakage;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentMizkar extends Fragment {
    private TextView txtTaghvim;
    private RelativeLayout layout_Kartabl;
    View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_mizkar, container, false);

//        bind_Layout ();
//
//        PersianDateMe dateNow = new PersianDateMe ();
//        FormatPersianDate pdformater1 = new FormatPersianDate ("Y/m/d");
//
//        txtTaghvim.setText (" امروز " + dateNow.dayName () + " مورخ "+ pdformater1.format (dateNow));
//
//        layout_Kartabl.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText (getActivity (), "Amaliyat move", Toast.LENGTH_LONG).show ();
//            }
//        });
//
        return rootView;
    }
//
//    private void bind_Layout() {
//
//        txtTaghvim = rootView.findViewById (R.id.textView_Taghvim);
//        layout_Kartabl = rootView.findViewById (R.id.layout_RelativeLayout_Kartabl);
//
//    }
}