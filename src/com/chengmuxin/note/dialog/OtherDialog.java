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

import com.chengmuxin.note.R;

public class OtherDialog extends Activity implements OnClickListener {
	private Button order, options;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, OtherDialog.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_other);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.gravity = Gravity.RIGHT;
		lp.y = -1000;
		getWindow().setAttributes(lp);
		init();
	}

	private void init() {
		order = (Button) findViewById(R.id.dialog_other_order);
		order.setOnClickListener(this);
		options = (Button) findViewById(R.id.dialog_other_options);
		options.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_other_order:
			OrderDialog.actionActivity(this);
			this.finish();
			break;
		case R.id.dialog_other_options:
			OptionsDialog.actionActivity(this);
			this.finish();
			break;

		default:
			break;
		}

	}
}
