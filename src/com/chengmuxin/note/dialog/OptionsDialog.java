package com.chengmuxin.note.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.chengmuxin.note.R;
import com.chengmuxin.note.activity.MainActivity;

public class OptionsDialog extends Activity implements OnClickListener {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private CheckBox bysummary;
	private Button cancel, ok;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, OptionsDialog.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_options);
		init();
	}

	private void init() {
		pref = getSharedPreferences("NotePara", 0);
		bysummary = (CheckBox) findViewById(R.id.dialog_options_bysummary);
		bysummary.setChecked(pref.getBoolean("summary", false));
		cancel = (Button) findViewById(R.id.dialog_options_cancel);
		cancel.setOnClickListener(this);
		ok = (Button) findViewById(R.id.dialog_options_ok);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_options_cancel:
			MainActivity.actionActivity(this);
			this.finish();
			break;
		case R.id.dialog_options_ok:
			//½«Ñ¡Ïî×´Ì¬´¢´æ
			editor = pref.edit();
			editor.putBoolean("summary", bysummary.isChecked());
			editor.commit();
			MainActivity.actionActivity(this);
			this.finish();
			break;

		default:
			break;
		}
	}
}
