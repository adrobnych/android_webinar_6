package com.example.todo;

import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.todo.model.ToDo;

public class NewToDoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_to_do);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_to_do, menu);
		return true;
	}
	
	public void onClick(View v){
		EditText task_et = (EditText) findViewById(R.id.task_et);
		EditText priority_et = (EditText) findViewById(R.id.priority_et);
		ToDoApp app = (ToDoApp) getApplication();

		ToDo todo = new ToDo();
		try {
			todo.setId(app.getTodoManager().getNextId());
			todo.setTask(task_et.getText().toString());
			todo.setPriority(Integer.valueOf(priority_et.getText().toString()));

			app.getTodoManager().create(todo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
	}

}
