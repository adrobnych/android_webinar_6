package com.example.todo;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.todo.model.ToDo;

public class ToDoDetailsActivity extends Activity {
	private int id; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_details);
		
		id = getIntent().getIntExtra("todoId", 0);
		
		TextView idTV = (TextView) findViewById(R.id.textView1);
		TextView taskTV = (TextView) findViewById(R.id.textView2);
		TextView priorityTV = (TextView) findViewById(R.id.textView3);
		
		ToDo todo = null;
		try {
			todo = ((ToDoApp)getApplication()).getTodoManager().findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idTV.setText(""+todo.getId());
		taskTV.setText(todo.getTask());
		priorityTV.setText(""+todo.getPriority());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_details, menu);
		return true;
	}
	
	public void onClickClose(View v){
		//Intent i = new Intent(ToDoDetailsActivity.this, MainActivity.class);
		//startActivity(i);
		finish();
	}

}
