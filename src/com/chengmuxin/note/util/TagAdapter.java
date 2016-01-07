package com.chengmuxin.note.util;

import java.util.List;

import com.chengmuxin.note.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TagAdapter extends ArrayAdapter<String> {
	private int resource;

	public TagAdapter(Context context, int resource, List<String> list) {
		super(context, resource, list);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder;
		String str = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(resource, null);
			viewHolder = new ViewHolder();
			viewHolder.tagname = (TextView) view
					.findViewById(R.id.taglist_tagname);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.tagname.setText(str);
		return view;
	}

	class ViewHolder {
		TextView tagname;
	}
}
