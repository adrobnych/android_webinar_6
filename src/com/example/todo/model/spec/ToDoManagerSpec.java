package com.example.todo.model.spec;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.todo.model.ToDo;
import com.example.todo.model.ToDoManager;
import com.example.todo.model.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ToDoManagerSpec {

	
	static ConnectionSource connectionSource = null;
	static ToDoManager todoMan = null;

	@BeforeClass
    public static void setUpDatabaseLayer() throws SQLException {
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, ToDo.class);
        
        todoMan = new ToDoManager();
       
		try {
			Dao<ToDo, Integer> todoDao = DaoManager.createDao(connectionSource, ToDo.class);
			todoMan.setTodoDao(todoDao);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

	@Before
	public void clearPupils(){
		try {
			TableUtils.clearTable(connectionSource, ToDo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void todoCanBestoredInDBUsingManager() {
		ToDo todo1 = new ToDo(1, "buy milk", 5);
		ToDo todo2 = new ToDo(2, "clean u monitor", 5);
		ToDo read_todo1 = null;
		ToDo read_todo2 = null;
		
		try {
			todoMan.create(todo1);
			read_todo1 = todoMan.getDao().queryForId(1);
			todoMan.create(todo2);
			read_todo2 = todoMan.getDao().queryForId(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("buy milk", read_todo1.getTask());
		assertEquals("clean u monitor", read_todo2.getTask());
		
	}
	
	@Test
	public void iCanGetNextID() {
		ToDo todo1 = new ToDo(1, "buy milk", 5);
		ToDo todo2 = new ToDo(2, "clean u monitor", 5);

		Integer nextId = -1;
		try {
			todoMan.create(todo1);
			todoMan.create(todo2);
			nextId = todoMan.getNextId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(Integer.valueOf(3), nextId);
		
		
	}

}
