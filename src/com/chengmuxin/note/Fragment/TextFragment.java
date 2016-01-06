package com.chengmuxin.note.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.activity.MainActivity;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.TimeUtil;

public class TextFragment extends Fragment implements OnClickListener {

	private Note note;
	private NoteDB noteDB;
	private ImageButton back, update, delete;
	private TextView title, content;
	public static final String PAR_KEY = "com.chengmuxin.note.contentPar";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_content_text, container, false);
		/*view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent ev) {
				switch (ev.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
					Toast.makeText(getActivity(), "按下", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
				return false;
			}
		});*/
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
		note.setLocaldate(TimeUtil.getDate());
		note.setLocaltime(TimeUtil.getTime());
		noteDB.updateNote(note);
	}

	private void init() {
		noteDB = NoteDB.getInstance(getActivity());
		note = (Note) getActivity().getIntent().getParcelableExtra(PAR_KEY);
		back = (ImageButton) getActivity().findViewById(R.id.text_back);
		back.setOnClickListener(this);
		update = (ImageButton) getActivity().findViewById(R.id.text_update);
		update.setOnClickListener(this);
		delete = (ImageButton) getActivity().findViewById(R.id.text_delete);
		delete.setOnClickListener(this);
		title = (TextView) getActivity().findViewById(R.id.text_title);
		title.setText(note.getTitle());
		content = (TextView) getActivity().findViewById(R.id.text_content);
		content.setText(note.getContent());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_back:
			MainActivity.actionActivity(getActivity());
			getActivity().finish();
			break;
		case R.id.text_update:
			getFragmentManager().beginTransaction()
					.replace(R.id.activity_content, new EditFragment())
					.addToBackStack(null).commit();
			break;
		case R.id.text_delete:
			noteDB.deleteNotes(note);
			// 待添加删除确认操作
			MainActivity.actionActivity(getActivity());
			getActivity().finish();
			break;
		default:
			break;
		}
	}

}
