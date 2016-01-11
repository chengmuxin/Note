package com.chengmuxin.note.util;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.model.Note;

public class NoteAdapter extends ArrayAdapter<Note> {
	private SharedPreferences pref;
	private Context context;
	private int resource;

	public NoteAdapter(Context context, int resource, List<Note> list) {
		super(context, resource, list);
		this.context = context;
		this.resource = resource;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder;
		Note note = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(resource, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view.findViewById(R.id.list_title);
			viewHolder.time = (TextView) view.findViewById(R.id.list_time);
			viewHolder.tag = (TextView) view.findViewById(R.id.list_tag);
			viewHolder.content = (TextView) view
					.findViewById(R.id.list_content);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		// 查询参数表
		pref = context.getSharedPreferences("NotePara", 0);
		viewHolder.title.setText(note.getTitle());
		// 显示时间
		String str = pref.getString("order", "");
		if ("modify".equals(str)) {
			if (TimeUtil.getDate().equals(note.getModifydate())) {
				viewHolder.time.setText(note.getModifytime());
			} else {
				viewHolder.time.setText(note.getModifydate());
			}
		} else if ("local".equals(str)) {
			if (TimeUtil.getDate().equals(note.getLocaldate())) {
				viewHolder.time.setText(note.getLocaltime());
			} else {
				viewHolder.time.setText(note.getLocaldate());
			}
		} else {
			if (TimeUtil.getDate().equals(note.getCreatedate())) {
				viewHolder.time.setText(note.getCreatetime());
			} else {
				viewHolder.time.setText(note.getCreatedate());
			}
		}
		viewHolder.tag.setText(note.getTag());
		Boolean b = pref.getBoolean("summary", true);
		if (!b) {
			viewHolder.content.setVisibility(View.GONE);
		}
		viewHolder.content.setText(note.getContent());
		return view;
	}

	class ViewHolder {
		TextView title;
		TextView time;
		TextView tag;
		TextView content;
	}

}
