package com.chengmuxin.note.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.dialog.OtherDialog;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.NoteAdapter;

public class FindActivity extends Activity implements OnClickListener {
	private NoteDB noteDB;
	private ListView listView;
	private List<Note> list;
	private NoteAdapter adapter;
	private EditText search;
	private ImageButton back, clear;
	private Button other;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, FindActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_find);
		init();
	}

	private void init() {
		noteDB = NoteDB.getInstance(this);
		listView = (ListView) findViewById(R.id.find_list);
		back = (ImageButton) findViewById(R.id.find_back);
		back.setOnClickListener(this);
		search = (EditText) findViewById(R.id.find_search);
		search.addTextChangedListener(new MyTextWatcher());
		clear = (ImageButton) findViewById(R.id.find_clear);
		clear.setVisibility(View.GONE);
		clear.setOnClickListener(this);
		other = (Button) findViewById(R.id.find_other);
		other.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.find_back:
			MainActivity.actionActivity(this);
			this.finish();
			break;
		case R.id.find_clear:
			search.setText("");
			clear.setVisibility(View.GONE);
			list.clear();
			adapter = new NoteAdapter(FindActivity.this, R.layout.activity_title, list);
			listView.setAdapter(adapter);
			break;
		case R.id.find_other:
			OtherDialog.actionActivity(this);
			break;
		default:
			break;
		}
	}
	
	private class MyTextWatcher implements TextWatcher{
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1,
				int arg2, int arg3) {
		}

		@Override
		public void afterTextChanged(Editable arg0) {
			if (!search.getText().toString().equals("")) {
				clear.setVisibility(View.VISIBLE);
				// ≤È—Ølist
				list = noteDB.selectNoteWhereTitle(search.getText().toString());
				adapter = new NoteAdapter(FindActivity.this, R.layout.activity_title, list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long arg3) {
						Note note = list.get(position);
						ContentActivity.actionActivity(FindActivity.this, note);
						FindActivity.this.finish();
					}
				});
			}else{
				clear.setVisibility(View.GONE);
				list.clear();
				adapter = new NoteAdapter(FindActivity.this, R.layout.activity_title, list);
				listView.setAdapter(adapter);
			}
			
		}
	}
}
