package com.chu.common.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;

import com.chu.batting.BattingController;
import com.chu.card.CardChange;
import com.chu.card.CardCompare;
import com.chu.card.CardOne;
import com.chu.card.CardRule;
import com.chu.card.controller.CardController;
import com.chu.common.exception.InvalidException;
import com.chu.player.Player;
import com.chu.player.controller.PlayerController;

public class CommonController {

	private ArrayList<Player> openList;
	ArrayList<Player> playerList;

	public void commonController() {
		while (true) {
			// 플레이어들 만드는 메소드
			try {
				playerList = PlayerController.createList();
				System.out.println(playerList);
				break;
			} catch (InputMismatchException e) {
				System.out.println("올바른 값을 입력하세요");
			}
		}

		CardController cardController = new CardController();
		for (Player p : playerList) {
			p.setMyList(cardController.cardHandling());
			System.out.println(p.getMyList());

		}
		// 카드 1개 골라서 지움
		for (Player p : playerList) {
			while (true) {
				try {
					cardController.cardRemove(p);
					break;
				} catch (InputMismatchException | InvalidException e) {
					e.printStackTrace();
				}
			}
			System.out.println(p.getMyList());
		}

		// 오픈할 카드 결정
		for (Player p : playerList) {
			while (true) {
				try {
					cardController.cardOpen(p);
					break;
				} catch (InputMismatchException | InvalidException e) {
					e.printStackTrace();
				}
			}
		}
		CardCompare cardCompare = new CardCompare();
		BattingController bc = new BattingController();
		// 게임 플레이를 위한 while

		// 게임 플레이를 위한 while
		while (true) {
			// 순서 정하기
			openList = cardCompare.compare(playerList);

			for (int i = 0; i < openList.size(); i++) {
				// TODO
				Player p = openList.get(i);
				if (bc.make(p)) {
					openList.remove(p);
				}

			}

			if (openList.get(0).getMyList().size() == 7) {
				// 최종 결과
				// 승리자 확인
				// 계속할지 판단
				ArrayList<CardOne> oneList = cardCompare.compare(playerList).get(0).getMyList();
				CardRule rule = new CardRule();
				int result = rule.cardProcess(oneList);
				CardChange cardChange = new CardChange();
				System.out.println(oneList);
				System.out.println(cardChange.changeString(result));
				break;
			}

			for (int i = 0; i < openList.size(); i++) {
				ArrayList l = cardController.cardHandling(1);
				openList.get(i).getOpenList().addAll(l);
				openList.get(i).getMyList().addAll(l);
			}

		}

	}
}
