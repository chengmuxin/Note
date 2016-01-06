package com.chengmuxin.note.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chengmuxin.note.model.Note;

public class NoteDB {
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "MyNote";

	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION = 1;

	private static NoteDB noteDB;

	private SQLiteDatabase db;

	/**
	 * �����췽��˽�л�
	 */
	private NoteDB(Context context) {
		NoteOpenHelper dbHelper = new NoteOpenHelper(context, DB_NAME, null,
				VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * ��ȡNoteDB��ʵ��
	 */
	public synchronized static NoteDB getInstance(Context context) {
		if (noteDB == null) {
			noteDB = new NoteDB(context);
		}
		return noteDB;
	}

	/**
	 * ��Noteʵ���洢�����ݿ�
	 */
	public void insertNote(Note note) {
		if (note != null) {
			ContentValues values = new ContentValues();
			values.put("title", note.getTitle());
			values.put("content", note.getContent());
			values.put("tag", note.getTag());
			values.put("createdate", note.getCreatedate());
			values.put("createtime", note.getCreatetime());
			values.put("modifydate", note.getModifydate());
			values.put("modifytime", note.getModifytime());
			values.put("localdate", note.getLocaldate());
			values.put("localtime", note.getLocaltime());
			db.insert("note", null, values);
		}
	}

	/**
	 * �����ݿ��ȡNote��Ϣ
	 */
	public List<Note> selectNotes(String order) {
		List<Note> list = new ArrayList<Note>();
		Cursor cursor;
		if ("create".equals(order)) {
			System.out.println("create");
			cursor = db
					.rawQuery(
							"select * from note order by createdate desc,createtime desc",
							null);
		} else if ("modify".equals(order)) {
			cursor = db.rawQuery(
					"select * from note order by modifydate,modifytime", null);
		} else if ("local".equals(order)) {
			cursor = db.rawQuery(
					"select * from note order by localdate,localtime", null);
		} else {
			cursor = db.rawQuery("select * from note", null);
		}
		if (cursor.moveToFirst()) {
			do {
				Note note = new Note();
				note.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
				note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				note.setContent(cursor.getString(cursor
						.getColumnIndex("content")));
				note.setTag(cursor.getString(cursor.getColumnIndex("tag")));
				note.setCreatedate(cursor.getString(cursor
						.getColumnIndex("createdate")));
				note.setCreatetime(cursor.getString(cursor
						.getColumnIndex("createtime")));
				note.setModifydate(cursor.getString(cursor
						.getColumnIndex("modifydate")));
				note.setModifytime(cursor.getString(cursor
						.getColumnIndex("modifytime")));
				note.setLocaldate(cursor.getString(cursor
						.getColumnIndex("localdate")));
				note.setLocaltime(cursor.getString(cursor
						.getColumnIndex("localtime")));
				list.add(note);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * ��ѯNote��Ϣ
	 */
	public List<Note> selectNoteWhereTitle(String str) {
		List<Note> list = new ArrayList<Note>();
		Cursor cursor = db.rawQuery("select * from note where title like ?",
				new String[] { str });
		if (cursor.moveToFirst()) {
			do {
				Note note = new Note();
				note.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
				note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				note.setContent(cursor.getString(cursor
						.getColumnIndex("content")));
				note.setTag(cursor.getString(cursor.getColumnIndex("tag")));
				note.setCreatedate(cursor.getString(cursor
						.getColumnIndex("createdate")));
				note.setCreatetime(cursor.getString(cursor
						.getColumnIndex("createtime")));
				note.setModifydate(cursor.getString(cursor
						.getColumnIndex("modifydate")));
				note.setModifytime(cursor.getString(cursor
						.getColumnIndex("modifytime")));
				note.setLocaldate(cursor.getString(cursor
						.getColumnIndex("localdate")));
				note.setLocaltime(cursor.getString(cursor
						.getColumnIndex("localtime")));
				list.add(note);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * �޸����ݿ��е�Noteʵ��
	 */
	public void updateNote(Note note) {
		if (note != null) {
			ContentValues values = new ContentValues();
			values.put("title", note.getTitle());
			values.put("content", note.getContent());
			values.put("tag", note.getTag());
			values.put("createdate", note.getCreatedate());
			values.put("createtime", note.getCreatetime());
			values.put("modifydate", note.getModifydate());
			values.put("modifytime", note.getModifytime());
			values.put("localdate", note.getLocaldate());
			values.put("localtime", note.getLocaltime());
			db.update("note", values, "_id = ?",
					new String[] { String.valueOf(note.get_id()) });
		}
	}

	/**
	 * ɾ�����ݿ��е�Noteʵ��
	 */
	public void deleteNotes(Note note) {
		db.delete("note", "_id = ?",
				new String[] { String.valueOf(note.get_id()) });
	}

	/**
	 * ��Tagʵ���洢�����ݿ�
	 */
	public void insertTag(String tag) {
		if (tag != null) {
			ContentValues values = new ContentValues();
			values.put("tagname", tag);
			db.insert("tag", null, values);
		}
	}

	/**
	 * �����ݿ��ȡTag��Ϣ
	 */
	public List<String> selectTags() {
		List<String> list = new ArrayList<String>();
		Cursor cursor = db.rawQuery("select * from tag", null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(cursor.getColumnIndex("tagname")));
			} while (cursor.moveToNext());
		}
		return list;
	}

}
