package com.chengmuxin.note.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
	private ScrollView scroll;
	private LinearLayout top, bottom;
	private Point down = new Point(), up = new Point();
	public static final String PAR_KEY = "com.chengmuxin.note.contentPar";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_content_text, container,
				false);
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
		top = (LinearLayout) getActivity().findViewById(R.id.text_top);
		top.setVisibility(View.VISIBLE);
		bottom = (LinearLayout) getActivity().findViewById(R.id.text_bottom);
		bottom.setVisibility(View.VISIBLE);
		scroll = (ScrollView) getActivity().findViewById(R.id.text_scroll);
		scroll.setOnTouchListener(new MyOnTouchListener());
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
			AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
			dialog.setTitle("Note");
			dialog.setMessage("您确定删除“" + note.getTitle() + "”吗？");
			dialog.setCancelable(false);
			dialog.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							noteDB.deleteNotes(note);
							MainActivity.actionActivity(getActivity());
							getActivity().finish();
						}
					});
			dialog.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
			break;
		default:
			break;
		}
	}

	/**
	 * 滑动方法内部类
	 * @author Administrator
	 * 
	 */
	private class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent ev) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				down.x = (int) ev.getX();
				down.y = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				up.x = (int) ev.getX();
				up.y = (int) ev.getY();
				if (up.y > down.y) { // 向下滑动
					top.setVisibility(View.VISIBLE);
					bottom.setVisibility(View.VISIBLE);
				} else { // 向上滑动
					top.setVisibility(View.GONE);
					bottom.setVisibility(View.GONE);
				}
				break;

			default:
				break;
			}
			return false;
		}
	}
}
