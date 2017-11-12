package com.coors.expensestudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.coors.expensestudy.Constant.REQUEST_CODE_ISLOGIN;

public class MainActivity extends AppCompatActivity {

    private SharedPrefHandler pref;
    private ListView listView;
    private String[] data;
    private ExpenseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkIsLogin(); // 檢查是否有登入過
        findViews();
        setUpCursorAdapter();
//        setUpArrayAdapter(); //初始化listView
//        ExpenseHelper helper = new ExpenseHelper(this, "expense.db", null, 1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToActivity.goTo(MainActivity.this, GoToActivity.ADD_ACTIVITY);
            }
        });
    }

    private void setUpCursorAdapter() {
        Cursor cursor = helper.getReadableDatabase().query(ExpenseHelper.TABLE_SCHEMA.TABLE_NAME, null, null, null, null, null, null, null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.listview_item_row,
                cursor,
                new String[]{ExpenseHelper.TABLE_SCHEMA.COL_CDATE,ExpenseHelper.TABLE_SCHEMA.COL_INFO, ExpenseHelper.TABLE_SCHEMA.COL_AMOUNT},
                new int[]{R.id.item_tv_cdate,R.id.item_tv_info,R.id.item_tv_amount},
                0);
        listView.setAdapter(simpleCursorAdapter);

    }

    private void setUpArrayAdapter() {
        data = getResources().getStringArray(R.array.list_view);
        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, data[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        listView = findViewById(R.id.listview);
        helper = ExpenseHelper.getInstance(this);

    }

    private void checkIsLogin() {
        pref = SharedPrefHandler.getInstance(this);
        if (!pref.getIsLogin()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ISLOGIN);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ISLOGIN:
                    Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    class ListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_row, null);
                TextView textView = convertView.findViewById(R.id.list_tv_itme_row);
                textView.setText(data[position]);
            }
            return convertView;
        }
    }

}
