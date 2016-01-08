package com.chengmuxin.note.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.chengmuxin.note.R;
import com.chengmuxin.note.activity.NewActivity;
import com.chengmuxin.note.activity.TagActivity;

public class MainOtherDialog extends Activity implements OnClickListener {
	private Button order, options;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, MainOtherDialog.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_main_other);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.RIGHT;
		lp.y = -1000;
		getWindow().setAttributes(lp);
		init();
	}

	private void init() {
		order = (Button) findViewById(R.id.dialog_main_other_order);
		order.setOnClickListener(this);
		options = (Button) findViewById(R.id.dialog_main_other_options);
		options.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_main_other_order:
//			MainOtherOrderDialog.actionActivity(this);
			NewActivity.actionActivity(this);
			this.finish();
			break;
		case R.id.dialog_main_other_options:
			Toast.makeText(this, "options", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

	}
}
