package com.coloreavee.setting;

import com.example.coloreavee.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	private MediaPlayer mPlayer;
	private int music_list = R.raw.nyancat;

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		mPlayer = MediaPlayer.create(MusicService.this, music_list);
		mPlayer.start();
		mPlayer.setLooping(true);
	}

	public void onDestroy() {
		mPlayer.stop();
		mPlayer.release();
		super.onDestroy();
	}
}
