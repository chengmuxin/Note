package com.chengmuxin.note.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chengmuxin.note.R;
import com.chengmuxin.note.DB.NoteDB;
import com.chengmuxin.note.model.Note;
import com.chengmuxin.note.util.TimeUtil;

public class EditFragment extends Fragment implements OnClickListener {

	private Note note;
	private NoteDB noteDB;
	private Button save, other;
	private EditText content;
	public final static String PAR_KEY = "com.chengmuxin.note.contentPar";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_content_edit, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		noteDB = NoteDB.getInstance(getActivity());
		note = (Note) getActivity().getIntent().getParcelableExtra(PAR_KEY);
		save = (Button) getActivity().findViewById(R.id.edit_save);
		save.setOnClickListener(this);
		other = (Button) getActivity().findViewById(R.id.edit_other);
		other.setOnClickListener(this);
		content = (EditText) getActivity().findViewById(R.id.edit_content);
		content.setText(note.getContent());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_save:
			note.setContent(content.getText().toString());
			note.setModifydate(TimeUtil.getDate());
			note.setModifytime(TimeUtil.getTime());
			noteDB.updateNote(note);

			getFragmentManager().beginTransaction()
					.replace(R.id.activity_content, new TextFragment())
					.commit();
			break;
		default:
			break;
		}
	}
}
