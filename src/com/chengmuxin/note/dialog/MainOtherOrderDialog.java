package com.chengmuxin.note.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.chengmuxin.note.R;
import com.chengmuxin.note.activity.MainActivity;

public class MainOtherOrderDialog extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private RadioButton bymodify, bycreate, bytitle, bylocal;
	private Button cancel;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, MainOtherOrderDialog.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_main_other_order);
		init();
	}

	private void init() {
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		bymodify = (RadioButton) findViewById(R.id.dialog_main_other_order_bymodify);
		bycreate = (RadioButton) findViewById(R.id.dialog_main_other_order_bycreate);
		bytitle = (RadioButton) findViewById(R.id.dialog_main_other_order_bytitle);
		bylocal = (RadioButton) findViewById(R.id.dialog_main_other_order_bylocal);
		bymodify.setOnCheckedChangeListener(this);
		bycreate.setOnCheckedChangeListener(this);
		bytitle.setOnCheckedChangeListener(this);
		bylocal.setOnCheckedChangeListener(this);
		String str = pref.getString("order", "").toString();
		System.out.println("51"+str);
		if("modify".equals(str)){
			bymodify.setChecked(true);
		}else if("create".equals(str)){
			bycreate.setChecked(true);
		}else if("title".equals(str)){
			bytitle.setChecked(true);
		}else if("local".equals(str)){
			bylocal.setChecked(true);
		}
		cancel = (Button) findViewById(R.id.dialog_main_other_order_cancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_main_other_order_cancel:
			MainActivity.actionActivity(this);
			this.finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton b, boolean arg1) {
		editor = pref.edit();
		switch (b.getId()) {
		case R.id.dialog_main_other_order_bymodify:
			editor.putString("order", "modify");
			break;
		case R.id.dialog_main_other_order_bycreate:
			editor.putString("order", "create");
			break;
		case R.id.dialog_main_other_order_bytitle:
			editor.putString("order", "title");
			break;
		case R.id.dialog_main_other_order_bylocal:
			editor.putString("order", "local");
			break;

		default:
			break;
		}
		editor.commit();
		String str = pref.getString("order", "").toString();
		System.out.println("100"+str);
		MainActivity.actionActivity(this);
		this.finish();
	}
}
