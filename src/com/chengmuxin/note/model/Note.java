package com.chengmuxin.note.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
	private int _id;
	private String title;
	private String content;
	private String tag;
	private String createdate;
	private String createtime;
	private String modifydate;
	private String modifytime;
	private String localdate;
	private String localtime;
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getLocaldate() {
		return localdate;
	}

	public void setLocaldate(String localdate) {
		this.localdate = localdate;
	}

	public String getLocaltime() {
		return localtime;
	}

	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}

	public static final Parcelable.Creator<Note> CREATOR = new Creator<Note>() {
		public Note createFromParcel(Parcel parcel) {
			Note note = new Note();
			note._id = parcel.readInt();
			note.title = parcel.readString();
			note.content = parcel.readString();
			note.tag = parcel.readString();
			note.createdate = parcel.readString();
			note.createtime = parcel.readString();
			note.modifydate = parcel.readString();
			note.modifytime = parcel.readString();
			note.localdate = parcel.readString();
			note.localtime = parcel.readString();
			return note;
		}

		public Note[] newArray(int size) {
			return new Note[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeInt(_id);
		parcel.writeString(title);
		parcel.writeString(content);
		parcel.writeString(tag);
		parcel.writeString(createdate);
		parcel.writeString(createtime);
		parcel.writeString(modifydate);
		parcel.writeString(modifytime);
		parcel.writeString(localdate);
		parcel.writeString(localtime);
	}
}
