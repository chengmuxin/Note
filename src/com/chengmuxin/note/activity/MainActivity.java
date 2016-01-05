package com.chengmuxin.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.chengmuxin.note.R;
import com.chengmuxin.note.Fragment.MainFragment;

public class MainActivity extends Activity {

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

}
