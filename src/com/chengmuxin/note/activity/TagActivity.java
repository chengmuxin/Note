package com.chengmuxin.note.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.util.TagAdapter;

public class TagActivity extends Activity implements OnClickListener {

	private NoteDB noteDB;
	private ImageButton back, delete;
	private Button save, add;
	private EditText edit;
	private TextView myTag;
	private ListView listView;
	private List<String> list;
	private TagAdapter adapter;
	private String tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tag);
		init();
	}

	private void init() {
		noteDB = NoteDB.getInstance(this);
		back = (ImageButton) findViewById(R.id.tag_back);
		back.setOnClickListener(this);
		save = (Button) findViewById(R.id.tag_save);
		save.setOnClickListener(this);
		add = (Button) findViewById(R.id.tag_add);
		add.setOnClickListener(this);
		edit = (EditText) findViewById(R.id.tag_edit);
		myTag = (TextView) findViewById(R.id.tag_myTag);
		delete = (ImageButton) findViewById(R.id.tag_delete);
		delete.setOnClickListener(this);
		listView = (ListView) findViewById(R.id.tag_list);
		list = noteDB.selectTags();
		adapter = new TagAdapter(this, R.layout.activity_taglist, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				tag = list.get(position);
				myTag.setText(tag);
				myTag.setVisibility(View.VISIBLE);
				delete.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tag_back:
			MainActivity.actionActivity(TagActivity.this);
			this.finish();
			break;
		case R.id.tag_save:
			Intent intent = new Intent();
			intent.putExtra("tag", tag);
			setResult(RESULT_OK, intent);
			finish();
			break;
		case R.id.tag_add:
			String tagStr = edit.getText().toString();
			if (tagStr != null) {
				noteDB.insertTag(tagStr);
				tag = tagStr;
				myTag.setText(tagStr);
				myTag.setVisibility(View.VISIBLE);
				delete.setVisibility(View.VISIBLE);
				list = noteDB.selectTags();
				adapter = new TagAdapter(this, R.layout.activity_taglist, list);
				listView.setAdapter(adapter);
			}
			break;
		case R.id.tag_delete:
			myTag.setText("");
			tag = null;
			myTag.setVisibility(View.INVISIBLE);
			delete.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}
	}
}
