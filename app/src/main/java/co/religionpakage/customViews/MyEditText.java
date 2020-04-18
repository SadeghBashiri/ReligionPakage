package co.religionpakage.customViews;

import android.content.Context;
//import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

//import advance.android.utils.BaseApplication;
import co.religionpakage.utils.BaseApplication;

public class MyEditText extends AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
        setTypeface(BaseApplication.appFace);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(BaseApplication.appFace);
    }

    public String text() {
        return (this.getText() != null ? this.getText().toString() : "");
    }
}
