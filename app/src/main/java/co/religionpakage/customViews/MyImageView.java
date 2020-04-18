package co.religionpakage.customViews;

import android.content.Context;
//import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

//import com.bumptech.glide.Glide;


public class MyImageView extends AppCompatImageView {

    Context mContext;

    public MyImageView(Context context) {
        super(context);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

//    public void load(String url) {
//        Glide.with(mContext).load(url).into(this);
//    }
}
