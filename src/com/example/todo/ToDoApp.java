package com.example.todo;

import java.sql.SQLException;

import com.example.todo.db.ToDoAppDBHelper;
import com.example.todo.model.ToDo;
import com.example.todo.model.ToDoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import android.app.Application;

public class ToDoApp extends Application{
	
	private ToDoManager todoManager = null;
	private ToDoAppDBHelper dbHelper = null;
	
	public ToDoApp(){
		super();
		try {
			dbHelper = new ToDoAppDBHelper(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ToDoManager getTodoManager() {
		if(todoManager == null){
			todoManager = new ToDoManager();
			try {
				Dao<ToDo, Integer> todoDao = DaoManager.createDao(dbHelper.getConnectionSource(), ToDo.class);
				todoManager.setTodoDao(todoDao);
				
				if(todoDao.countOf() == 0){
					todoDao.create(new ToDo(1, "buy milk", 5));
					todoDao.create(new ToDo(2, "buy bred", 5));
					todoDao.create(new ToDo(3, "buy beer", 5));
					todoDao.create(new ToDo(4, "buy apples", 5));
					todoDao.create(new ToDo(1, "buy milk", 5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			todoManager.getTodos().add(
//					new ToDo(1, "buy milk", 5));
//			todoManager.getTodos().add(
//					new ToDo(2, "buy bred", 5));
//			todoManager.getTodos().add(
//					new ToDo(3, "buy beer", 5));
//			todoManager.getTodos().add(
//					new ToDo(4, "buy apples", 5));
//			todoManager.getTodos().add(
//					new ToDo(5, "buy bananas", 10));
//			todoManager.getTodos().add(
//					new ToDo(6, "buy TV", 5));
//			todoManager.getTodos().add(
//					new ToDo(7, "buy laptop", 5));
//			todoManager.getTodos().add(
//					new ToDo(8, "buy iphone", 5));
//			todoManager.getTodos().add(
//					new ToDo(9, "buy car", 5));
//			todoManager.getTodos().add(
//					new ToDo(10, "buy dog", 5));
		}
			
		return todoManager;
	}
	
	

}
