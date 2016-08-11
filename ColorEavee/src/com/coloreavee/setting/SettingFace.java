package com.coloreavee.setting;

import com.coloreavee.start.StartFace;
import com.example.coloreavee.R;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingFace extends Activity {
	private ImageView back_BTN, toggle_BTN, music_down, music_up;
	private TextView music_loud;
	private Intent intent = new Intent();
	private Intent music_intent = new Intent();
	private int music_toggle = 0;
	private AudioManager aManager;
	private String music_loud_number = "";
	private Animation touch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_face);

		intent.setClass(this, StartFace.class);
		touch = AnimationUtils.loadAnimation(this, R.anim.touch);

		music_down = (ImageView) findViewById(R.id.down_music);
		music_up = (ImageView) findViewById(R.id.up_music);
		music_loud = (TextView) findViewById(R.id.music_loud);

		aManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		music_loud_number = String.valueOf(aManager.getStreamVolume(AudioManager.STREAM_MUSIC));
		music_loud.setText(music_loud_number);

		music_down.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				music_down.startAnimation(touch);
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 1);
				music_loud_number = String.valueOf(aManager.getStreamVolume(AudioManager.STREAM_MUSIC));
				music_loud.setText(music_loud_number);
			}
		});
		music_up.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				music_up.startAnimation(touch);
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 1);
				music_loud_number = String.valueOf(aManager.getStreamVolume(AudioManager.STREAM_MUSIC));
				music_loud.setText(music_loud_number);
			}
		});

		music_intent.setClass(this, MusicService.class);

		Intent music_Toggle = getIntent();
		music_toggle = music_Toggle.getIntExtra("music_Toggle", 0);

		toggle_BTN = (ImageView) findViewById(R.id.music_Toggle);
		if (music_toggle == 0) {
			toggle_BTN.setImageResource(R.drawable.musicoff);
		} else {
			toggle_BTN.setImageResource(R.drawable.musicon);
		}

		toggle_BTN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				toggle_BTN.startAnimation(touch);
				if (music_toggle == 0) {
					stopService(music_intent);
					music_toggle = 1;
					toggle_BTN.setImageResource(R.drawable.musicon);
				} else {
					startService(music_intent);
					music_toggle = 0;
					toggle_BTN.setImageResource(R.drawable.musicoff);
				}
			}
		});

		back_BTN = (ImageView) findViewById(R.id.back_setting_to_start);
		back_BTN.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				back_BTN.startAnimation(touch);
				intent.putExtra("music_Toggle", music_toggle);
				startActivity(intent);
			}
		});
	}
}
