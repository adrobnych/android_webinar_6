package com.example.todo.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "todos")
public class ToDo {
	@DatabaseField(id = true, canBeNull = false)
	private Integer id;
	@DatabaseField(columnName = "task", canBeNull = false, index = true, indexName = "task_index")
	private String task;
	@DatabaseField(columnName = "priority", canBeNull = false, index = true, indexName = "priority_index")
	private Integer priority;
	
	
	public ToDo() {
		super();
	}
	
	public ToDo(Integer id, String task, Integer priority) {
		super();
		this.id = id;
		this.task = task;
		this.priority = priority;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
