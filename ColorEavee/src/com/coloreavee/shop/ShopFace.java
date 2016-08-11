package com.coloreavee.shop;

import com.coloreavee.attribute.AttrDataBase;
import com.coloreavee.start.StartFace;
import com.example.coloreavee.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopFace extends Activity {
	private ImageView hp_img, dmg_img, time_img;
	private ImageView back_BTN;
	private TextView hp_up_lv, hp_up_money, dmg_up_lv, dmg_up_money, time_up_lv, time_up_money;
	private TextView all_money;
	private int money;
	private Intent intent;
	private AttrDataBase attrDB;
	private int music_toggle = 0;
	private Animation touch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_face);

		attrDB = new AttrDataBase(this);

		money = attrDB.getMoney();

		all_money = (TextView) findViewById(R.id.shop_money);
		all_money.setText("钱袋： $" + money);

		hp_up_lv = (TextView) findViewById(R.id.hp_up_lv);
		hp_up_money = (TextView) findViewById(R.id.hp_up_money);
		dmg_up_lv = (TextView) findViewById(R.id.dmg_up_lv);
		dmg_up_money = (TextView) findViewById(R.id.dmg_up_money);
		time_up_lv = (TextView) findViewById(R.id.time_up_lv);
		time_up_money = (TextView) findViewById(R.id.time_up_money);

		hp_up_lv.setText("第" + attrDB.getHP_level() + "级");
		dmg_up_lv.setText("第" + attrDB.getDMG_level() + "级");
		time_up_lv.setText("第" + attrDB.getTime_level() + "级");

		hp_up_money.setText("$" + String.valueOf(attrDB.getHP_level() * 50 + 50));
		dmg_up_money.setText("$" + String.valueOf(attrDB.getDMG_level() * 50 + 50));
		time_up_money.setText("$" + String.valueOf(attrDB.getTime_level() * 50 + 50));

		hp_img = (ImageView) findViewById(R.id.hp_up_img);
		dmg_img = (ImageView) findViewById(R.id.dmg_up_img);
		time_img = (ImageView) findViewById(R.id.time_up_img);

		touch = AnimationUtils.loadAnimation(this, R.anim.touch);

		hp_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast t = Toast.makeText(ShopFace.this, "金钱不足", Toast.LENGTH_SHORT);
				if (money >= attrDB.getHP_level() * 50 + 50) {
					money -= attrDB.getHP_level() * 50 + 50;
					attrDB.setMoney(money);
					all_money.setText("钱袋： $" + attrDB.getMoney());
					attrDB.setHP_level(attrDB.getHP_level() + 1);
					hp_up_lv.setText("第" + attrDB.getHP_level() + "级");
					hp_up_money.setText(String.valueOf(attrDB.getHP_level() * 50 + 50));
					hp_img.startAnimation(touch);
				} else {
					t.show();
				}
			}
		});
		dmg_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast t = Toast.makeText(ShopFace.this, "金钱不足", Toast.LENGTH_SHORT);
				if (money >= attrDB.getDMG_level() * 50 + 50) {
					money -= attrDB.getDMG_level() * 50 + 50;
					attrDB.setMoney(money);
					all_money.setText("钱袋： $" + attrDB.getMoney());
					attrDB.setDMG_level(attrDB.getDMG_level() + 1);
					dmg_up_lv.setText("第" + attrDB.getDMG_level() + "级");
					dmg_up_money.setText(String.valueOf(attrDB.getDMG_level() * 50 + 50));
					dmg_img.startAnimation(touch);
				} else {
					t.show();
				}
			}
		});
		time_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast t = Toast.makeText(ShopFace.this, "金钱不足", Toast.LENGTH_SHORT);
				if (money >= attrDB.getTime_level() * 50 + 50) {
					money -= attrDB.getTime_level() * 50 + 50;
					attrDB.setMoney(money);
					all_money.setText("钱袋： $" + attrDB.getMoney());
					attrDB.setTime_level(attrDB.getTime_level() + 1);
					time_up_lv.setText("第" + attrDB.getTime_level() + "级");
					time_up_money.setText(String.valueOf(attrDB.getTime_level() * 50 + 50));
					time_img.startAnimation(touch);
				} else {
					t.show();
				}
			}
		});

		Intent music_Toggle = getIntent();
		music_toggle = music_Toggle.getIntExtra("music_Toggle", 0);

		intent = new Intent(this, StartFace.class);
		back_BTN = (ImageView) findViewById(R.id.back_shop_to_start);
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
