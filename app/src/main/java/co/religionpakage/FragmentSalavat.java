package co.religionpakage;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import co.religionpakage.customViews.MyImageView;
import co.religionpakage.customViews.MyTextView;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link FragmentSalavat#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FragmentSalavat extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public FragmentSalavat() {
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FragmentSalavat.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FragmentSalavat newInstance(String param1, String param2) {
//        FragmentSalavat fragment = new FragmentSalavat();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_salavat, container, false);
//    }
//    // Required empty public constructor


    private MyTextView txt_counter;
    private RelativeLayout layout_count;
    private int counter = 1;
    View rootView;
    MediaPlayer mediaPlayer = null;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_salavat, container, false);
        layout_count = rootView.findViewById(R.id.layout_count);
        txt_counter = rootView.findViewById(R.id.txt_counter);
        layout_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                PlayerExample playerexample = new PlayerExample();
                playerexample.playSound(getActivity().getApplicationContext(),"salavat-1.mp3");
            }
        });

        return rootView;
    }
    public class PlayerExample {
//        MediaPlayer mediaPlayer = null;

        public void playSound(final Context context, final String fileName) {
            mediaPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor afd = context.getAssets().openFd(fileName);
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "Dast_Nevis.ttf");
            txt_counter.setTypeface(typeface);
            txt_counter.setText(counter++ + "");
        }
    }
    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
 }
