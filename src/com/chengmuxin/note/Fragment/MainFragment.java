package com.chengmuxin.note.Fragment;

import java.util.List;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.activity.ContentActivity;
import com.chengmuxin.note.activity.FindActivity;
import com.chengmuxin.note.activity.NewActivity;
import com.chengmuxin.note.dialog.OtherDialog;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.NoteAdapter;

public class MainFragment extends Fragment implements OnClickListener {
	private NoteDB noteDB;
	private ListView listView;
	private List<Note> list;
	private NoteAdapter adapter;
	private SharedPreferences pref;
	private ImageButton find, add;
	private Button other;

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
		String str = pref.getString("order", "");
		refreshList(str);
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
		pref = getActivity().getSharedPreferences("NotePara", 0);
		listView = (ListView) getActivity().findViewById(R.id.main_list);
		find = (ImageButton) getActivity().findViewById(R.id.main_find);
		find.setOnClickListener(this);
		add = (ImageButton) getActivity().findViewById(R.id.main_add);
		add.setOnClickListener(this);
		other = (Button) getActivity().findViewById(R.id.main_other);
		other.setOnClickListener(this);
	}
	
	private void refreshList(String str){
		list = noteDB.selectNotes(str);
		adapter = new NoteAdapter(getActivity(), R.layout.activity_title, list);
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_find:
			FindActivity.actionActivity(getActivity());
			break;
		case R.id.main_add:
			NewActivity.actionActivity(getActivity());
			break;
		case R.id.main_other:
			OtherDialog.actionActivity(getActivity());
			break;
		
		default:
			break;
		}
	}
}
