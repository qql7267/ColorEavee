package com.coloreavee.assistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HealthPointProgress extends View {
	private static final int[] SECTION_COLORS = { Color.RED, Color.GREEN, Color.BLUE };
	private float maxHP;
	private float currentHP;
	private Paint mPaint;
	private int mWidth, mHeight;

	public HealthPointProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void initView(Context context) {
	}

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		int round = mHeight / 2;
		mPaint.setColor(Color.rgb(60, 60, 60));
		RectF rectBg = new RectF(0, 0, mWidth, mHeight);
		canvas.drawRoundRect(rectBg, round, round, mPaint);
		mPaint.setColor(Color.BLACK);
		RectF rectBlackBg = new RectF(2, 2, mWidth - 2, mHeight - 2);
		canvas.drawRoundRect(rectBlackBg, round, round, mPaint);

		float section = currentHP / maxHP;
		RectF rectProgressBg = new RectF(3, 3, (mWidth - 3) * section, mHeight - 3);
		if (section <= 1.0f / 3.0f) {
			if (section != 0.0f) {
				mPaint.setColor(SECTION_COLORS[0]);
			} else {
				mPaint.setColor(Color.TRANSPARENT);
			}
		} else {
			int count = (section <= 1.0f / 3.0f * 2) ? 2 : 3;
			int[] colors = new int[count];
			System.arraycopy(SECTION_COLORS, 0, colors, 0, count);
			float[] positions = new float[count];
			if (count == 2) {
				positions[0] = 0.0f;
				positions[1] = 1.0f - positions[0];
			} else {
				positions[0] = 0.0f;
				positions[1] = (maxHP / 3) / currentHP;
				positions[2] = 1.0f - positions[0] * 2;
			}
			positions[positions.length - 1] = 1.0f;
			LinearGradient shader = new LinearGradient(3, 3, (mWidth - 3) * section, mHeight - 3, colors, null,
					Shader.TileMode.MIRROR);
			mPaint.setShader(shader);
		}
		canvas.drawRoundRect(rectProgressBg, round, round, mPaint);
	}

	private int dipTopX(int dip) {
		float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
	}

	public void setMaxHP(float maxHP) {
		this.maxHP = maxHP;
	}

	public void setCurrentHP(float currentHP) {
		this.currentHP = currentHP > maxHP ? maxHP : currentHP;
		invalidate();
	}

	public float getMaxHP() {
		return maxHP;
	}

	public float getCurrentHP() {
		return currentHP;
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
			mWidth = widthSpecSize;
		} else {
			mWidth = 0;
		}
		if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
			mHeight = dipTopX(15);
		} else {
			mHeight = heightSpecSize;
		}
		setMeasuredDimension(mWidth, mHeight);
	}
}