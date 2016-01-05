package com.chengmuxin.note.Fragment;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.activity.ContentActivity;
import com.chengmuxin.note.activity.NewActivity;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.NoteAdapter;

public class MainFragment extends Fragment {
	private NoteDB noteDB;
	private ListView listView;
	private List<Note> list;
	private NoteAdapter adapter;
	private ImageButton add;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	@Override
	public void onResume() {
		super.onResume();
		list = noteDB.selectNotes("create");
		adapter = new NoteAdapter(getActivity(), R.layout.activity_title, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Note note = list.get(position);
				ContentActivity.actionActivity(getActivity(), note);
			}
		});
	}

	private void init() {
		noteDB = NoteDB.getInstance(getActivity());
		listView = (ListView) getActivity().findViewById(R.id.main_list);
		add = (ImageButton) getActivity().findViewById(R.id.main_add);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NewActivity.actionActivity(getActivity());
			}
		});
	}
}
