package co.religionpakage.customViews;

import android.content.Context;
//import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

//import advance.android.utils.BaseApplication;
import co.religionpakage.utils.BaseApplication;

public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        setTypeface(BaseApplication.appFace);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(BaseApplication.appFace);
    }


}
