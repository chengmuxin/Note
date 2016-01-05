package com.chengmuxin.note.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteOpenHelper extends SQLiteOpenHelper {

	public static final String CREATE_NOTE = "create table note ("
			+ "_id integer primary key autoincrement, "
			+ "title text, content text, tag text, "
			+ "createdate text, createtime text, "
			+ "modifydate text, modifytime text, "
			+ "localdate text, localtime text )";

	public static final String CREATE_TAG = "create table tag ("
			+ "_id integer primary key autoincrement, tagname text )";

	public NoteOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_NOTE);
		db.execSQL(CREATE_TAG);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
