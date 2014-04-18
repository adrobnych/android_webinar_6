package com.example.todo.model.spec;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.todo.model.ToDo;
import com.example.todo.model.spec.db.TestDbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ToDoDAOSpec {
	
	static Dao<ToDo, Integer> todoDao = null;
	static ConnectionSource connectionSource = null;

	@BeforeClass
    public static void setUpDatabaseLayer() throws SQLException {
        connectionSource = new TestDbHelper().getConnectionSource();
        TableUtils.createTableIfNotExists(connectionSource, ToDo.class);
       
		try {
			todoDao = DaoManager.createDao(connectionSource, ToDo.class);
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
	public void todoCanBestoredInDB() {
		ToDo todo1 = new ToDo(1, "buy milk", 5);
		ToDo todo2 = new ToDo(2, "clean u monitor", 5);
		ToDo read_todo1 = null;
		ToDo read_todo2 = null;
		
		try {
			todoDao.create(todo1);
			read_todo1 = todoDao.queryForId(1);
			todoDao.create(todo2);
			read_todo2 = todoDao.queryForId(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("buy milk", read_todo1.getTask());
		assertEquals("clean u monitor", read_todo2.getTask());
		
	}

}
