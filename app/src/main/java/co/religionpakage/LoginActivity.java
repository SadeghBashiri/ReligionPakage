package co.religionpakage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import co.religionpakage.utils.BaseActivity;
import co.religionpakage.utils.Gen;

public class LoginActivity extends BaseActivity implements Contract.View {
    LinearLayout mLayout;
    private EditText password;
    private TextView errorMassage;
    private ImageButton imageButton_showOrHidePassword;
    private AutoCompleteTextView username;
    private Button btnLogIn;
    // private CheckBox saveInformation;
    private String sUserName, sPassword;
    private ProgressDialog dialog;
    private int passwordNotVisible = 1;

    Contract.Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature (Window.FEATURE_NO_TITLE); //will hide the title
        // Objects.requireNonNull (getSupportActionBar ()).hide (); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_login);

        bind();

        mLayout.setOnClickListener(null);

        username.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                usernameOperation();
            }
        });

        imageButton_showOrHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordOperation();
            }
        });
        bindDialog();
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);
                checkOperation();
            }
        });

//            presenter.attachView(this);

    }

    @Override
    public void onReceived(String model) {

    }

    @Override
    public void receivedFailed(String msg) {

    }

    private void bind() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogIn = findViewById(R.id.btnLogIn);
        errorMassage = findViewById(R.id.errorMassage);
        imageButton_showOrHidePassword = findViewById(R.id.imageButton_showOrHidePassword);
        mLayout = findViewById(R.id.login_activity);

        //saveInformation = findViewById (R.id.saveInformation);
        db.deleteTanzimat();
        final String[] listUser = db.SelectAllDataUser();

        ArrayAdapter<String> adapterlistUser = new ArrayAdapter<> //<String>
                (this, android.R.layout.select_dialog_item, listUser);

        username.setThreshold(1);//will start working from first character
        username.setAdapter(adapterlistUser);//setting the adapter data into the AutoCompleteTextView
//        bindDialog();
    }

    private void bindString() {
        sUserName = username.getText().toString() + "";
        sPassword = password.getText().toString() + "";
    }

    private void bindDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("لطفا صبر کنید...");
        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.onBackPressed();
    }

    private void usernameOperation() {
        password.setText(db.getPassword(sUserName));
        password.setBackgroundColor(Color.parseColor("#fff3e0"));
        username.setBackgroundColor(Color.parseColor("#fff3e0"));
    }

    private void passwordOperation() {

        if (passwordNotVisible == 1) {
            //show password
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageButton_showOrHidePassword.setImageResource(R.drawable.ic_vector_visibility_on);
            passwordNotVisible = 0;

        } else {
            //hide password
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageButton_showOrHidePassword.setImageResource(R.drawable.ic_vector_visibility_off);
            passwordNotVisible = 1;

        }

    }

    private void checkOperation() {
        bindString();
        if(sUserName.matches("^[a-zA-Z0-9._-]{3,}$")){
            postInformation();
        } else {
            username.setError("لطفأ نام کاربری را صحیح وارد نمایید");
        }
    }

    private void postInformation() {
        db.insertUser_TaLogIn(sUserName, sPassword);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivityForResult(intent, 160);
    }
}
