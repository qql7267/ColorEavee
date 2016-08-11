package com.coloreavee.game;

import com.coloreavee.assistant.HealthPointProgress;
import com.coloreavee.attribute.AttrDataBase;
import com.coloreavee.start.StartFace;
import com.example.coloreavee.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class GameFace extends Activity {
	private ImageView bullet_1_red, bullet_2_orangle, bullet_3_yellow, bullet_4_green, bullet_5_blue, bullet_6_cyan;
	private ImageView target, eavee, boss, judge;
	private HealthPointProgress My_HP, Boss_HP;
	private TextView mission, countdown, game_money;
	private String target_Boss = "";
	private AttrDataBase attrDB;
	private int mission_n, hp_n, dmg_n, time_n, money_n = 0;
	private CountDownTimer timer;
	private Rand rand = new Rand();
	private Dialog fin_dlg = null;
	private Intent intent_to_start;
	private int music_toggle = 0;
	private Animation anim_eavee_defeat, anim_boss_victory, anim_eavee_victory, anim_boss_defeat;
	private Animation touch;
	private AnimationDrawable anim_judge;
	private int judge_f = 0;

	private static final int VICTORY_DIALOG = 1;
	private static final int DEFRAT_DIALOG = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_face);

		attrDB = new AttrDataBase(this);

		mission_n = attrDB.getMission();
		hp_n = attrDB.getHP_level();
		dmg_n = attrDB.getDMG_level();
		time_n = attrDB.getTime_level();

		My_HP = (HealthPointProgress) findViewById(R.id.my_HP);
		My_HP.setMaxHP(hp_n * 10 + 90);
		My_HP.setCurrentHP(hp_n * 10 + 90);

		Boss_HP = (HealthPointProgress) findViewById(R.id.boss_HP);
		Boss_HP.setMaxHP(98 + mission_n * 2);
		Boss_HP.setCurrentHP(98 + mission_n * 2);

		game_money = (TextView) findViewById(R.id.game_money);

		mission = (TextView) findViewById(R.id.mission);
		mission.setText(String.valueOf("第 " + mission_n + " 关"));

		target = (ImageView) findViewById(R.id.target);
		target_Boss = rand.getRand(6);
		setTarget(target_Boss);

		Intent music_Toggle = getIntent();
		music_toggle = music_Toggle.getIntExtra("music_Toggle", music_toggle);

		eavee = (ImageView) findViewById(R.id.eavee);
		boss = (ImageView) findViewById(R.id.boss);
		judge = (ImageView) findViewById(R.id.judge);
		anim_eavee_defeat = AnimationUtils.loadAnimation(this, R.anim.defeat_eavee);
		anim_boss_defeat = AnimationUtils.loadAnimation(this, R.anim.defeat_boss);
		anim_eavee_victory = AnimationUtils.loadAnimation(this, R.anim.victory_eavee);
		anim_boss_victory = AnimationUtils.loadAnimation(this, R.anim.victory_boss);

		bullet_1_red = (ImageView) findViewById(R.id.bullet_1_red);
		bullet_2_orangle = (ImageView) findViewById(R.id.bullet_2_orangle);
		bullet_3_yellow = (ImageView) findViewById(R.id.bullet_3_yellow);
		bullet_4_green = (ImageView) findViewById(R.id.bullet_4_green);
		bullet_5_blue = (ImageView) findViewById(R.id.bullet_5_blue);
		bullet_6_cyan = (ImageView) findViewById(R.id.bullet_6_cyan);

		countdown = (TextView) findViewById(R.id.countdown);
		touch = AnimationUtils.loadAnimation(this, R.anim.touch);

		timer = new CountDownTimer((time_n + 30) * 1000, 1000) {
			public void onTick(long millisUntilFinished) {
				countdown.setText(String.valueOf(millisUntilFinished / 1000));
			}

			public void onFinish() {
				defeat();
			}
		};
		timer.start();

		bullet_1_red.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("1");
				bullet_1_red.startAnimation(touch);
			}
		});
		bullet_2_orangle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("2");
				bullet_2_orangle.startAnimation(touch);
			}
		});
		bullet_3_yellow.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("3");
				bullet_3_yellow.startAnimation(touch);
			}
		});
		bullet_4_green.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("4");
				bullet_4_green.startAnimation(touch);
			}
		});
		bullet_5_blue.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("5");
				bullet_5_blue.startAnimation(touch);
			}
		});
		bullet_6_cyan.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DeductHP("6");
				bullet_6_cyan.startAnimation(touch);
			}
		});
	}

	public void DeductHP(String bullet) {
		if (judge_f == 1)
			anim_judge.stop();
		if (target_Boss.contains(bullet)) {
			judge.setBackgroundResource(R.anim.judge_true);
			anim_judge = (AnimationDrawable) judge.getBackground();
			anim_judge.setOneShot(true);
			anim_judge.start();
			Boss_HP.setCurrentHP(Boss_HP.getCurrentHP() - 8 - dmg_n * 2);
			money_n += 1;
			game_money.setText("获得: $" + money_n);
		} else {
			judge.setBackgroundResource(R.anim.judge_false);
			anim_judge = (AnimationDrawable) judge.getBackground();
			anim_judge.setOneShot(true);
			anim_judge.start();
			My_HP.setCurrentHP(My_HP.getCurrentHP() - 8 - mission_n / 10);
		}
		if (Boss_HP.getCurrentHP() <= 0)
			victory();
		if (My_HP.getCurrentHP() <= 0)
			defeat();
		target_Boss = rand.getRand(6);
		setTarget(target_Boss);
		judge_f = 1;
	}

	public void setTarget(String target_Str) {
		if (target_Str.contains("1"))
			target.setBackgroundResource(R.drawable.rebtarget);
		if (target_Str.contains("2"))
			target.setBackgroundResource(R.drawable.orangletarget);
		if (target_Str.contains("3"))
			target.setBackgroundResource(R.drawable.yellowtarget);
		if (target_Str.contains("4"))
			target.setBackgroundResource(R.drawable.greentarget);
		if (target_Str.contains("5"))
			target.setBackgroundResource(R.drawable.bluetarget);
		if (target_Str.contains("6"))
			target.setBackgroundResource(R.drawable.cyantarget);
	}

	@SuppressWarnings("deprecation")
	public void victory() {
		timer.cancel();
		attrDB.setMoney(attrDB.getMoney() + money_n);
		eavee.startAnimation(anim_eavee_victory);
		boss.startAnimation(anim_boss_victory);
		money_n = 0;
		showDialog(VICTORY_DIALOG);
	}

	@SuppressWarnings("deprecation")
	public void defeat() {
		timer.cancel();
		attrDB.setMoney(attrDB.getMoney() + money_n);
		eavee.startAnimation(anim_eavee_defeat);
		boss.startAnimation(anim_boss_defeat);
		money_n = 0;
		showDialog(DEFRAT_DIALOG);
	}

	@SuppressLint("CutPasteId")
	protected Dialog onCreateDialog(int id) {
		intent_to_start = new Intent(this, StartFace.class);
		switch (id) {
		case VICTORY_DIALOG:
			fin_dlg = new Dialog(this);
			fin_dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
			fin_dlg.setContentView(R.layout.finish_dialog);
			ImageView final_Img_victory = (ImageView) fin_dlg.findViewById(R.id.final_img);
			final_Img_victory.setImageResource(R.drawable.victory);
			ImageView next_Img = (ImageView) fin_dlg.findViewById(R.id.next_or_again);
			next_Img.setImageResource(R.drawable.nextmissionbutton);
			next_Img.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mission_n += 1;
					attrDB.setMission(mission_n);
					allReturn();
					eavee.clearAnimation();
					boss.clearAnimation();
					fin_dlg.cancel();
				}
			});
			ImageView back_Img_victory = (ImageView) fin_dlg.findViewById(R.id.finish_back);
			back_Img_victory.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					intent_to_start.putExtra("music_Toggle", music_toggle);
					startActivity(intent_to_start);
				}
			});
			return fin_dlg;
		case DEFRAT_DIALOG:
			fin_dlg = new Dialog(this);
			fin_dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
			fin_dlg.setContentView(R.layout.finish_dialog);
			ImageView final_Img_defeat = (ImageView) fin_dlg.findViewById(R.id.final_img);
			final_Img_defeat.setImageResource(R.drawable.defeat);
			ImageView again_Img = (ImageView) fin_dlg.findViewById(R.id.next_or_again);
			again_Img.setImageResource(R.drawable.againbutton);
			again_Img.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					allReturn();
					eavee.clearAnimation();
					boss.clearAnimation();
					fin_dlg.cancel();
				}
			});
			ImageView back_Img_defeat = (ImageView) fin_dlg.findViewById(R.id.finish_back);
			back_Img_defeat.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					intent_to_start.putExtra("music_Toggle", 1);
					startActivity(intent_to_start);
				}
			});
			return fin_dlg;
		}
		return null;
	}

	public void allReturn() {
		My_HP.setMaxHP(hp_n * 10 + 90);
		My_HP.setCurrentHP(hp_n * 10 + 90);
		Boss_HP.setMaxHP(98 + mission_n * 2);
		Boss_HP.setCurrentHP(98 + mission_n * 2);
		mission.setText(String.valueOf("第 " + mission_n + " 关"));
		game_money.setText("获得: $0");
		timer.start();
	}
}