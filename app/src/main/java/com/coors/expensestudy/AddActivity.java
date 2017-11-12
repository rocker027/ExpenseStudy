package com.coors.expensestudy;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coors.expensestudy.utils.GoToActivity;
import com.coors.expensestudy.utils.ShowToast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCdate;
    private EditText etInfo;
    private EditText etAmount;
    private Button btnAdd;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
    }

    private void findViews() {
        etCdate = findViewById(R.id.add_et_date);
        etInfo = findViewById(R.id.add_et_info);
        etAmount = findViewById(R.id.add_et_amount);
        btnAdd = findViewById(R.id.add_btn_add);
        btnCancel = findViewById(R.id.add_btn_cancel);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn_add:
                doVerification();
                break;
            case R.id.add_btn_cancel:
                GoToActivity.goTo(this,GoToActivity.HOME_MAIN_ACTIVITY);
                break;
        }
    }

    private void doVerification() {
        if (etCdate.getText().toString().trim().matches("") || etInfo.getText().toString().trim().matches("") || etAmount.getText().toString().trim().matches("")) {
            ShowToast.getInstance(this).show("欄位均不可為空值");
            showError();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ExpenseHelper.TABLE_SCHEMA.COL_CDATE,etCdate.getText().toString());
            contentValues.put(ExpenseHelper.TABLE_SCHEMA.COL_INFO,etInfo.getText().toString());
            contentValues.put(ExpenseHelper.TABLE_SCHEMA.COL_AMOUNT,etAmount.getText().toString());
            long id = ExpenseHelper.getInstance(this).getWritableDatabase().insert(ExpenseHelper.TABLE_SCHEMA.TABLE_NAME, null, contentValues);
            if (id != -1) {
                ShowToast.getInstance(this).show("新增成功");
            } else {
                ShowToast.getInstance(this).show("新增失敗");
            }

        }
    }

    private void showError() {
        ArrayList<EditText> editTexts = new ArrayList<>();
        editTexts.add(etInfo);
        editTexts.add(etCdate);
        editTexts.add(etAmount);
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().matches("")) {
                editText.setError("不可空值");
            }
        }
    }
}
