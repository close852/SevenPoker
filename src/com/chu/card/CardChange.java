package com.chu.card;

public class CardChange {

	private final String[] RULE = { "TOP", "ONE_PAIR", "TWO_PAIR", "TRIPLE", "STRAIGHT", "BACK_STRAIGHT", "MOUNTAIN",
			"FLUSH", "FULL_HOUSE", "FOUR_CARD", "STRAIGHT_FLUSH", "BACK_STRAIGHT_FLUSH", "ROYAL_STRAIGHT_FLUSH" };

	private final String[] SHAPE = { "♣", "♡", "◇", "♠" };
	private final String[] NUMBER = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	public int changeNumber(CardOne one) {
		return one.getCard();
	}

	public String changeString(int num) {
		int rule = num / 1000;
		String result = "";
		result = RULE[rule];
		String number = NUMBER[(num - rule * 1000) / 10 - 2];
		String shape = SHAPE[num % 10 - 1];

		return number + " " + result;
	}

}
