package com.chengmuxin.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.chengmuxin.note.R;
import com.chengmuxin.note.Fragment.MainFragment;

public class MainActivity extends Activity {
	long lastClick;

	public static void actionActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getFragmentManager().beginTransaction()
				.replace(R.id.activity_main, new MainFragment()).commit();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - lastClick <= 1000) {
				return super.onKeyDown(keyCode, event);
			}
			lastClick = System.currentTimeMillis();
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

}
