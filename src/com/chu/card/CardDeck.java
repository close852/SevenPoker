package com.chu.card;

import java.util.ArrayList;

public class CardDeck {

	private ArrayList<CardOne> deck;
	private int deckSize = CardOne.SHAPE.length * CardOne.NUMBER.length;

	public CardDeck() {
		deck = new ArrayList<>(CardOne.SHAPE.length * CardOne.NUMBER.length);
		make_CardCase();
	}

	private void make_CardCase() {
		int count = 0;// 입력되는 cardcase의 index
		while (true) {
			CardOne card = new CardOne();
			// cardCase의 입력을 할 때, 같은 값이 있는지 판단 있으면 true 이고
			// 결과를 부정해서 false일떄 if문수행
			// System.out.println(count++ + "/" + cardCase.length);
			if (!duplicate(card, count)) {
				deck.add(card);
				count++;
			}

			if (deckSize == deck.size()) {// 조건을 충족하면,while 빠져나옴
				break;
			}
		}

	}

	private boolean duplicate(CardOne card, int count) {
		boolean isc = false;
		for (int i = 0; i < count; i++) {
			if (deck.get(i).equals(card)) {
				isc = true;// 중복이 되면 true
				break;
			}
		}
		return isc;
	}

	public ArrayList<CardOne> getDeck() {
		return deck;
	}

}
