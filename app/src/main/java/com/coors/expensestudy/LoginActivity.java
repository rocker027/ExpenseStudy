package com.coors.expensestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String USER_NAME = "userName";
    public static final String USER_PASSWORD = "userPassword";

    private EditText etUser;
    private EditText etPassword;
    private CheckBox cbRemeberUser;
    private SharedPrefHandler pref;
    private Button btnLogin;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
    }

    private void findViews() {
        etUser = findViewById(R.id.login_et_user);
        etPassword = findViewById(R.id.login_et_password);
        cbRemeberUser = findViewById(R.id.login_cb_remeber_user);
        btnLogin = findViewById(R.id.login_btn_login);
        btnCancel = findViewById(R.id.login_btn_cancel);
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        pref = SharedPrefHandler.getInstance(this);

        // 取得是否有登入過
        boolean islogin = pref.getIsLogin();
        cbRemeberUser.setChecked(islogin); // checkBox 設定是否打V
        if (islogin == true) {
            etUser.setText(pref.getUserName());
        }

        cbRemeberUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pref.setIsLogin(isChecked);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                doClickLogin();
                break;
            case R.id.login_btn_cancel:
                break;
        }
    }

    public void doClickLogin() {
        if ("rocker".equals(etUser.getText().toString()) && "1234".equals(etPassword.getText().toString())) {
            if (cbRemeberUser.isChecked() == true) {
                pref.setUserName(etUser.getText().toString());
            }

            getIntent().putExtra(USER_NAME, etUser.getText().toString());
            getIntent().putExtra(USER_PASSWORD, etPassword.getText().toString());
            setResult(RESULT_OK, getIntent());
            finish();
        } else {
            Toast.makeText(this, "user or password is error !!", Toast.LENGTH_SHORT).show();
        }
    }
}
