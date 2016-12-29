package com.chu.card;

/**
 * <b>카드 한장을 만드는 클래스</b>
 * 
 * @author 최지우
 * @version 3.0B
 * @since 2016.12.22
 */
public class CardOne implements Comparable<CardOne> {

	private String card;

	public static final String[] SHAPE = { "♣", "♡", "◇", "♠" };
	public static final String[] NUMBER = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	public CardOne() {
		makeCard();
	}

	public CardOne(String str) {
		this.card = str;
	}

	public int getCard() {
		return getShape() + getNumber();
	}

	public int getShape() {
		String num = card.charAt(0) + "";
		int result = 0;
		if (num.equals("♣")) {
			result = 1;
		} else if (num.equals("♡")) {
			result = 2;
		} else if (num.equals("◇")) {
			result = 3;
		} else if (num.equals("♠")) {
			result = 4;
		}

		return result;
	}

	public int getNumber() {
		String num = card.substring(1);
		int result = 0;
		if (num.equals("A")) {
			result = 14;
		} else if (num.equals("J")) {
			result = 11;
		} else if (num.equals("Q")) {
			result = 12;
		} else if (num.equals("K")) {
			result = 13;
		} else {
			result = Integer.valueOf(num);
		}
		return result * 10;
	}

	private void makeCard() {
		int a = (int) (Math.random() * SHAPE.length); // idx
		int b = (int) (Math.random() * NUMBER.length);// idx
		card = SHAPE[a] + NUMBER[b];
	}

	@Override
	public int hashCode() {
		return card.hashCode() + 137;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isc = false;
		CardOne cardOne = (CardOne) obj;
		if (card.equals(cardOne.card)) {
			isc = true;
		}
		return isc;
	}

	@Override
	public String toString() {
		return card;
	}

	@Override
	public int compareTo(CardOne o) {
		int result = 0;
		if (this.getNumber() == o.getNumber()) {
			result = 0;
		} else if (this.getNumber() > o.getNumber()) {
			result = 1;
		} else if (this.getNumber() < o.getNumber()) {
			result = -1;
		}
		return result;
	}

}
