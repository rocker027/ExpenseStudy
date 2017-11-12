package com.coors.expensestudy.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.coors.expensestudy.ExpenseHelper;

import static com.coors.expensestudy.ExpenseHelper.ExpContracts.TABLE_NAME;
import static com.coors.expensestudy.ExpenseHelper.TAG;


/**
 * Created by z8v on 2017/11/12.
 */

public class ExpenseProvider extends ContentProvider {
    public static final UriMatcher sUriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);
    public static final int EXPENSES = 200;
    public static final int EXPENSES_WITH_ID = 201;
    static {
        sUriMatcher.addURI(ExpenseHelper.ExpContracts.AUTHORITY,
                ExpenseHelper.ExpContracts.TABLE_NAME,
                EXPENSES);
        sUriMatcher.addURI(ExpenseHelper.ExpContracts.AUTHORITY,
                ExpenseHelper.ExpContracts.TABLE_NAME + "/#",
                EXPENSES_WITH_ID);
    }


    private ExpenseHelper helper;

    @Override
    public boolean onCreate() {
        helper = ExpenseHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case EXPENSES:
                break;
            case EXPENSES_WITH_ID:
                selection = selection == null ? "" : selection;
                selection = new StringBuilder(selection)
                        .append(ExpenseHelper.ExpContracts.TableExp.COL_ID)
                        .append("=")
                        .append(uri.getLastPathSegment())
                        .toString();
                Log.d(TAG, "query: selection " +selection);
                break;
        }

        Cursor cursor = helper.getReadableDatabase().query(ExpenseHelper.ExpContracts.TABLE_NAME,
                projection, selection, selectionArgs, sortOrder, null, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
