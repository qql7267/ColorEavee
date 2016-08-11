package com.coloreavee.attribute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AttrDataBase extends SQLiteOpenHelper {
	public static final int VERSION = 1;
	public static final String DATABASE_NAME = "attr.db";

	public AttrDataBase(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table attrs (" + "id integer not null unique primary key autoincrement,"
				+ "mission integer not null," + "hp_level integer not null," + "dmg_level integer not null,"
				+ "time_level integer not null," + "money integer not null)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void Initial() {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		// cv.put("id", 1);
		cv.put("mission", 1);
		cv.put("hp_level", 1);
		cv.put("dmg_level", 1);
		cv.put("time_level", 1);
		cv.put("money", 1000000);
		db.insert("attrs", null, cv);
	}

	public int getInitial() {
		int id = 0;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select id from attrs", null);
		if (c != null) {
			while (c.moveToNext())
				id = c.getInt(0);
			c.close();
		}
		return id;
	}

	public void delInitial(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		String whereClause = "id=?";
		String[] whereArgs = new String[] { String.valueOf(value) };
		db.delete("attrs", whereClause, whereArgs);
	}

	public int getMission() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select mission from attrs", null);
		c.moveToNext();
		int mission = c.getInt(0);
		c.close();
		return mission;
	}

	public int getHP_level() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select hp_level from attrs", null);
		c.moveToNext();
		int hp_level = c.getInt(0);
		c.close();
		return hp_level;
	}

	public int getDMG_level() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select dmg_level from attrs", null);
		c.moveToNext();
		int dmg_level = c.getInt(0);
		c.close();
		return dmg_level;
	}

	public int getTime_level() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select time_level from attrs", null);
		c.moveToNext();
		int time_level = c.getInt(0);
		c.close();
		return time_level;
	}

	public int getMoney() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("select money from attrs", null);
		c.moveToNext();
		int money = c.getInt(0);
		c.close();
		return money;
	}

	public void setMission(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("mission", value);
		String whereClause = "id=?";
		String[] whereArgs = new String[] { "1" };
		db.update("attrs", cv, whereClause, whereArgs);
	}

	public void setHP_level(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("hp_level", value);
		String whereClause = "id=?";
		String[] whereArgs = new String[] { "1" };
		db.update("attrs", cv, whereClause, whereArgs);
	}

	public void setDMG_level(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("dmg_level", value);
		String whereClause = "id=?";
		String[] whereArgs = new String[] { "1" };
		db.update("attrs", cv, whereClause, whereArgs);
	}

	public void setTime_level(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("time_level", value);
		String whereClause = "id=?";
		String[] whereArgs = new String[] { "1" };
		db.update("attrs", cv, whereClause, whereArgs);
	}

	public void setMoney(int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("money", value);
		String whereClause = "id=?";
		String[] whereArgs = new String[] { "1" };
		db.update("attrs", cv, whereClause, whereArgs);
	}
}
