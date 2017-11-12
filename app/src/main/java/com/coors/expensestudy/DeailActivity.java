package com.coors.expensestudy;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coors.expensestudy.utils.GoToActivity;

public class DeailActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deail);
        findViews();
    }

    private void findViews() {
        TextView tvCdate = findViewById(R.id.deail_tv_cdate);
        TextView tvInfo = findViewById(R.id.deail_tv_info);
        TextView tvAmount = findViewById(R.id.deail_tv_amount);
        Button btnBack = findViewById(R.id.deail_btn_back);
        btnBack.setOnClickListener(this);

        String cDate = "";
        String info = "";
        String amount = "";


        int id = getIntent().getIntExtra("postion",-1);
        if (id != -1) {
            Uri uri = ContentUris.withAppendedId(ExpenseHelper.ExpContracts.CONTENT_URI,id+1);
            Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
            cursor.moveToNext();
            cDate = cursor.getString(cursor.getColumnIndex(ExpenseHelper.ExpContracts.TableExp.COL_CDATE));
            info = cursor.getString(cursor.getColumnIndex(ExpenseHelper.ExpContracts.TableExp.COL_INFO));
            amount = cursor.getString(cursor.getColumnIndex(ExpenseHelper.ExpContracts.TableExp.COL_AMOUNT));

            tvCdate.setText(cDate);
            tvInfo.setText(info);
            tvAmount.setText(amount);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deail_btn_back:
                GoToActivity.goTo(this, GoToActivity.HOME_MAIN_ACTIVITY);
                break;
        }
    }
}
