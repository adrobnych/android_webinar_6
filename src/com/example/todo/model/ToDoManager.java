package com.example.todo.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

public class ToDoManager {
	private Dao<ToDo, Integer> todoDao = null;

	public void setTodoDao(Dao<ToDo, Integer> todoDao) {
		this.todoDao = todoDao;
	}

	public List<ToDo> getTodos() throws SQLException {
		return todoDao.queryForAll();
	}
	
	public ToDoManager() {
		super();
	}

	public void removeAtId(int id) throws SQLException {
		todoDao.deleteById(id);
		
	}

	public ToDo findById(int id) throws SQLException {
		return todoDao.queryForId(id);
	}

	public Integer getNextId() throws SQLException {
		QueryBuilder<ToDo, Integer> qBuilder = todoDao.queryBuilder();
		qBuilder.orderBy("id", false);
		qBuilder.limit(1L);
		ToDo todoWithMaxId = todoDao.queryForFirst(qBuilder.prepare());
		if(todoWithMaxId != null)
			return todoWithMaxId.getId() + 1;
		else
			return null;
	}

	public void create(ToDo todo) throws SQLException {
		todoDao.create(todo);
	}

	public Dao<ToDo, Integer> getDao() {
		return todoDao;
	}

}
