package com.coloreavee.game;

import java.util.Random;

public class Rand {
	public String getRandAll(int amount) {
		Random rand = new Random();
		String randStr = "";
		int randInt[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int randFlag, flag;

		for (int i = 0; i < amount; i++) {
			flag = 1;
			randFlag = rand.nextInt(amount);
			for (int j = 0; j < i; j++) {
				if (randFlag == randInt[j]) {
					flag = 0;
					i--;
					break;
				}
			}
			if (flag == 1) {
				randInt[i] = randFlag;
				randStr += String.valueOf(randFlag + 1);
			}
		}
		return randStr;
	}

	public String getRand(int amount) {
		Random rand = new Random();
		int randInt = rand.nextInt(amount)+1;
		String randStr = String.valueOf(randInt);
		return randStr;
	}
}
