package com.chengmuxin.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.chengmuxin.note.R;
import com.chengmuxin.note.Fragment.TextFragment;
import com.chengmuxin.note.model.Note;

public class ContentActivity extends Activity {

	public final static String PAR_KEY = "com.chengmuxin.note.contentPar";

	public static void actionActivity(Context context, Note note) {
		Intent intent = new Intent(context, ContentActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(PAR_KEY, note);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_content);
		getFragmentManager().beginTransaction()
				.replace(R.id.activity_content, new TextFragment()).commit();
	}

}
