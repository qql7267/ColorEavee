package com.coloreavee.start;

import com.coloreavee.attribute.AttrDataBase;
import com.coloreavee.game.GameFace;
import com.coloreavee.setting.MusicService;
import com.coloreavee.setting.SettingFace;
import com.coloreavee.shop.ShopFace;
import com.example.coloreavee.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StartFace extends Activity {
	private ImageView game_img, shop_img, set_img;
	private Intent in_to_game, in_to_shop, in_to_set;
	private AttrDataBase attrDB;
	private int music_toggle = 0;
	private Animation touch;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_face);

		Intent intent = getIntent();
		music_toggle = intent.getIntExtra("music_Toggle", 0);
		touch = AnimationUtils.loadAnimation(this, R.anim.touch);

		if (music_toggle == 0) {
			Intent music_intent = new Intent(this, MusicService.class);
			startService(music_intent);
		}

		attrDB = new AttrDataBase(this);
		attrDB.Initial();
		if (attrDB.getInitial() != 1) {
			attrDB.delInitial(attrDB.getInitial());
		}

		in_to_game = new Intent(this, GameFace.class);
		in_to_shop = new Intent(this, ShopFace.class);
		in_to_set = new Intent(this, SettingFace.class);

		game_img = (ImageView) findViewById(R.id.to_game);
		shop_img = (ImageView) findViewById(R.id.to_shop);
		set_img = (ImageView) findViewById(R.id.to_set);

		game_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				game_img.startAnimation(touch);
				in_to_game.putExtra("music_Toggle", music_toggle);
				startActivity(in_to_game);
			}
		});
		shop_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shop_img.startAnimation(touch);
				in_to_shop.putExtra("music_Toggle", music_toggle);
				startActivity(in_to_shop);
			}
		});
		set_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				set_img.startAnimation(touch);
				in_to_set.putExtra("music_Toggle", music_toggle);
				startActivity(in_to_set);
			}
		});
	}
}