package com.example.todo;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.model.ToDo;

public class ToDoAdapter extends BaseAdapter{
	private List<ToDo> todos;
	private LayoutInflater inflater;
	
	private class ViewHolder{
		public ImageView iv;
		public TextView tv;
	}

	public ToDoAdapter(Context context, List<ToDo> todos){
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.todos = todos;
	}
	
	@Override
	public int getCount() {
		if(todos != null)
			return todos.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(todos != null && position >=0 && position < getCount())
			return todos.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if(todos != null && position >=0 && position < getCount())
			return todos.get(position).getId();
		return 0;
	}

	@Override
public View getView(int position, View convertView, ViewGroup parent) {
        
        View       view = convertView; 
        ViewHolder viewHolder;
        
        if (view == null) {
            view = inflater.inflate(R.layout.todo_item, parent, false);
            
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) view.findViewById(R.id.imageView1);
            viewHolder.tv = (TextView) view.findViewById(R.id.textView1);
            
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        
        ToDo record = todos.get(position);
        
        //viewHolder.iv.setImageResource(record.getPicture());
        viewHolder.tv.setText("" + record.getTask() + ": " + record.getPriority());
        viewHolder.tv.setTextColor(Color.BLUE);
        
        return view;
    }

}
