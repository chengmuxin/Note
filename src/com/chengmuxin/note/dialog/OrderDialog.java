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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.chengmuxin.note.R;

public class OrderDialog extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private RadioButton bymodify, bycreate, bytitle, bylocal;
	private Button cancel;
	private long lastClick;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, OrderDialog.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_order);
		init();
	}

	private void init() {
		pref = getSharedPreferences("NotePara", 0);
		lastClick = System.currentTimeMillis();
		bymodify = (RadioButton) findViewById(R.id.dialog_order_bymodify);
		bycreate = (RadioButton) findViewById(R.id.dialog_order_bycreate);
		bytitle = (RadioButton) findViewById(R.id.dialog_order_bytitle);
		bylocal = (RadioButton) findViewById(R.id.dialog_order_bylocal);
		bymodify.setOnCheckedChangeListener(this);
		bycreate.setOnCheckedChangeListener(this);
		bytitle.setOnCheckedChangeListener(this);
		bylocal.setOnCheckedChangeListener(this);
		String str = pref.getString("order", "");
		if ("modify".equals(str)) {
			bymodify.setChecked(true);
		} else if ("create".equals(str)) {
			bycreate.setChecked(true);
		} else if ("title".equals(str)) {
			bytitle.setChecked(true);
		} else if ("local".equals(str)) {
			bylocal.setChecked(true);
		}
		cancel = (Button) findViewById(R.id.dialog_order_cancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_order_cancel:
			this.finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton b, boolean isChecked) {
		if (isChecked) {
			editor = pref.edit();
			switch (b.getId()) {
			case R.id.dialog_order_bymodify:
				editor.putString("order", "modify");
				break;
			case R.id.dialog_order_bycreate:
				editor.putString("order", "create");
				break;
			case R.id.dialog_order_bytitle:
				editor.putString("order", "title");
				break;
			case R.id.dialog_order_bylocal:
				editor.putString("order", "local");
				break;
			default:
				break;
			}
			editor.commit();
			if (System.currentTimeMillis() - lastClick > 100) {
				OrderDialog.this.finish();
			}
		}
	}
}
