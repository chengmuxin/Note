package com.chengmuxin.note.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.model.Note;

public class FindActivity extends Activity implements OnClickListener {
	private NoteDB noteDB;
	private List<Note> list;
	private EditText search;
	private ImageButton back, clear;

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
		back = (ImageButton) findViewById(R.id.find_back);
		back.setOnClickListener(this);
		search = (EditText) findViewById(R.id.find_search);
		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
			@Override
			public void afterTextChanged(Editable arg0) {
				//≤È—Ø
				list = noteDB.selectNoteWhereTitle(search.getText().toString());
			}
		});
		clear=(ImageButton) findViewById(R.id.find_clear);
		clear.setVisibility(View.GONE);
		clear.setOnClickListener(this);
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
			//
			break;
		default:
			break;
		}
	}
}
