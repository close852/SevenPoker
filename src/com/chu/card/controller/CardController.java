package com.chu.card.controller;

import java.util.ArrayList;

import com.chu.card.CardDeck;
import com.chu.card.CardOne;
import com.chu.common.InputNum;
import com.chu.common.exception.InvalidException;
import com.chu.player.Player;

public class CardController {

	ArrayList<CardOne> cards;

	public CardController() {
		CardDeck deck = new CardDeck();
		this.cards = (ArrayList<CardOne>) deck.getDeck();

	}

	public ArrayList<CardOne> cardHandling(int num) {

		ArrayList<CardOne> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(cards.remove(0));
		}
		return list;
	}

	public ArrayList<CardOne> cardHandling() {
		return cardHandling(4);
	}

	public void cardRemove(Player p) throws InvalidException {
		System.out.println(p.getMyList());
		System.out.println("몇번 지울래");
		int num = InputNum.input();
		ArrayList<CardOne> list = p.getMyList();
		if (num <= list.size() && num > 0) {
			p.getMyList().remove(num - 1);
		} else {
			throw new InvalidException();
		}

	}

	public void cardOpen(Player p) throws InvalidException {
		System.out.println(p.getMyList());
		System.out.println("공개할 카드를 선택");
		ArrayList<CardOne> list = p.getMyList();
		int num = InputNum.input();

		if (num > 0 && num <= list.size()) {
			p.setOpenList(p.getMyList().get(num - 1));
		} else {
			throw new InvalidException();
		}

	}

}
