package com.example.todo;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.todo.model.ToDoManager;

public class MainActivity extends Activity {
	ToDoManager todoManager = null;
	ListView todoList = null;
	ToDoAdapter a; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		todoManager = ((ToDoApp)getApplication()).getTodoManager();
		try {
			a = new ToDoAdapter(getApplicationContext(), todoManager.getTodos());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		todoList = (ListView) findViewById(R.id.listView1);
		
		todoList.setAdapter(a);
		
		todoList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> l, View v,
					int position, long id) {
				try {
					todoManager.removeAtId((int)id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					a = new ToDoAdapter(getApplicationContext(), todoManager.getTodos());
					todoList.setAdapter(a);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		});
		
		todoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				Intent i = new Intent(MainActivity.this, ToDoDetailsActivity.class);
				i.putExtra("todoId", (int)id);
				startActivity(i);
				
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			a = new ToDoAdapter(getApplicationContext(), todoManager.getTodos());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		todoList.setAdapter(a);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch(item.getItemId()){
		case R.id.action_settings:
			i = new Intent(Intent.ACTION_DIAL, null);
			startActivity(i);
		case R.id.add_todo_item:
			i = new Intent(MainActivity.this, NewToDoActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

}
