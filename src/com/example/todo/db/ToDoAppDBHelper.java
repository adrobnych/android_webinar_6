package com.example.todo.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.model.ToDo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ToDoAppDBHelper extends OrmLiteSqliteOpenHelper {
	private static final String TAG = "com.droidbrew.todoapp.db.ToDoAppDBHelper";
    private static final String DB_NAME = "todoapp.db";
    private static final int DB_VERSION = 1;
    private Context context;

    public ToDoAppDBHelper(Context context) throws SQLException {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTableIfNotExists(connectionSource, ToDo.class);

        } catch (java.sql.SQLException e) {
            Log.e(TAG, "onCreate", e);
        } 
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Context getContext() {
        return context;
    }
}