package com.chengmuxin.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.TimeUtil;

public class NewActivity extends Activity implements OnClickListener {
	private Note note = new Note();
	private NoteDB noteDB;
	private Button save, other;
	private ImageButton tag;
	private EditText title, content;
	public static final int REQUEST = 1;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, NewActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_new);
		init();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST:
			if (resultCode == RESULT_OK) {
				note.setTag(data.getStringExtra("tag"));
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void init() {
		noteDB = NoteDB.getInstance(this);
		save = (Button) findViewById(R.id.new_save);
		save.setOnClickListener(this);
		other = (Button) findViewById(R.id.new_other);
		other.setOnClickListener(this);
		title = (EditText) findViewById(R.id.new_title);
		tag = (ImageButton) findViewById(R.id.new_tag);
		tag.setOnClickListener(this);
		content = (EditText) findViewById(R.id.new_content);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_save:
			String stitle = title.getText().toString().trim();
			String scontent = content.getText().toString().trim();
			String date = TimeUtil.getDate();
			String time = TimeUtil.getTime();
			if (stitle.isEmpty()) {
				if (scontent.isEmpty()) {
					Toast.makeText(this, "不能创建空笔记", Toast.LENGTH_SHORT).show();
					MainActivity.actionActivity(NewActivity.this);
					this.finish();
					break;
				}
				note.setTitle(scontent.substring(0, scontent.length() > 8 ? 8
						: scontent.length()));
			} else {
				note.setTitle(stitle);
			}
			note.setContent(scontent);
			note.setCreatedate(date);
			note.setCreatetime(time);
			note.setModifydate(date);
			note.setModifytime(time);
			note.setLocaldate(date);
			note.setLocaltime(time);

			noteDB.insertNote(note);
			MainActivity.actionActivity(NewActivity.this);
			this.finish();
			break;
		case R.id.new_other:

			break;
		case R.id.new_tag:
			Intent intent = new Intent(NewActivity.this, TagActivity.class);
			startActivityForResult(intent, REQUEST);
			break;

		default:
			break;
		}
	}
}
