package com.chu.card;

import java.util.ArrayList;
import java.util.Collections;

import com.chu.common.constant.PorkerConstant;

public class CardRule {

	// 숫자 pair 묶은 메소드
	private ArrayList<CardOne> pairList;

	private ArrayList<CardOne> flushList;

	private ArrayList<CardOne> straightList;

	public CardRule() {
		pairList = new ArrayList<>();
		flushList = new ArrayList<>();
		straightList = new ArrayList<>();
	}

	public void print() {

	}

	private int flushCheck(ArrayList<CardOne> list) {
		int result = 0;
		CardOne temp = null;
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count = 0;
			for (CardOne o : list) {
				if (o.getShape() == list.get(i).getShape()) {
					count++;
					temp = o;
				}
			}
		}
		if (count >= 5) {
			result = PorkerConstant.FLUSH + temp.getCard();
		}
		return result;
	}

	private int straightCheck(ArrayList<CardOne> list) {
		int result = 0;
		@SuppressWarnings("unchecked")
		ArrayList<CardOne> temp = (ArrayList<CardOne>) list.clone();
		ArrayList<CardOne> tempS = null;
		for (CardOne c : list) {
			if (c.getNumber() == 140) {
				temp.add(0, c);
			}
		}
		int n = temp.size();
//		System.out.println(temp);
		for (int i = 0; i <= n - 5; i++) {
			int sum = 0;
			int count = 0;
			tempS = new ArrayList<>();
			for (int j = i; j < 5 + i; j++) {

				sum += temp.get(j).getNumber() / 10;

				if (j - 1 >= 0 && temp.get(j - 1).getNumber() / 10 + 1 == (temp.get(j).getNumber() / 10)) {
					tempS.add(temp.get(j - 1));
					count++;
				} else {
					count = 0;
				}
				if (count == 4) {
					count++;
					tempS.add(temp.get(j));
					break;
				}
			}
			if (count == 5) {
				result = sum;
				straightList = tempS;
			}
		}
		return result;
	}

	public int cardProcess(ArrayList<CardOne> cards) {
		// pair확인 할 list를 초기화
		setCharList(cards);
		// flush확인 할 list를 초기화
		setNumList(cards);
		int result = 0;
		ArrayList<CardOne> sortList = (ArrayList<CardOne>) cards.clone();
		Collections.sort(sortList);
		int straight = straightCheck(sortList);
		// straight가 0이 아니면
		if (straight != 0) {
			// straight 이면서 flush냐
			if (0 != flushCheck(straightList)) {
				if (straight == 60) {
					result = PorkerConstant.ROYAL_STRAIGHT_FLUSH + straightList.get(straightList.size() - 1).getCard();
				} else if (straight == 28) {
					result = PorkerConstant.BACK_STRAIGHT_FLUSH + straightList.get(straightList.size() - 1).getCard();
				} else {
					result = PorkerConstant.STRAIGHT_FLUSH + straightList.get(straightList.size() - 1).getCard();
				}
			} else {
				if (straight == 60) {
					result = PorkerConstant.MOUNTAIN + straightList.get(straightList.size() - 1).getCard();
				} else if (straight == 28) {
					result = PorkerConstant.BACK_STRAIGHT + straightList.get(straightList.size() - 1).getCard();
				} else {

					result = PorkerConstant.STRAIGHT + straightList.get(straightList.size() - 1).getCard();
				}
			}
		} else {
			if (flushList.size() >= 5) {
				result = flushCheck(flushList);
			} else {
				result = pair();
				if (result == 0) {
					result = noPair(cards);
				}
			}
		}
		return result;
	}

	// noPair일 때 처리
	private int noPair(ArrayList<CardOne> cards) {
		int max = 0;
		for (int i = 0; i < cards.size(); i++) {
			max = Math.max(max, cards.get(i).getCard());
		}
		return max;
	}

	// //페어 수정할 것(가장 큰거 리턴하도록)
	//
	// 페어 list 리턴하고
	// 6개인거
	//
	// 트리플 + 트리플
	// 포카드 + 원페어
	// 투페어 + 원페어
	//
	// 7장
	// 포카드 , 트리플
	// 풀하우스 , 원페어

	// pair 처리

	private void setNumList(ArrayList<CardOne> cards) {
		for (int i = 2; i < 14; i++) {
			int numCount = 0;
			ArrayList<CardOne> temp = new ArrayList<>();
			for (int j = 0; j < cards.size(); j++) {
				if (i == cards.get(j).getNumber() / 10) {
					numCount++;
					temp.add(cards.get(j));
				}
			}
			if (numCount > 1) {
				pairList.addAll(temp);
			}
		}
	}

	// noPair 처리 안됨
	private int pair() {
		int n = pairList.size();
		Collections.sort(pairList);
		int result = 0;
		if (n == 0) {
			result = 0;
		} else if (n == 2) {
			result = PorkerConstant.ONE_PAIR + pairList.get(n - 1).getCard();
		} else if (n == 3) {
			result = PorkerConstant.TRIPLE + pairList.get(n - 1).getCard();
		} else if (n == 4 && pairList.get(0).getNumber() == pairList.get(n - 1).getNumber()) {
			result = PorkerConstant.FOUR_CARD + pairList.get(n - 1).getCard();
		} else if (n == 4) {
			result = PorkerConstant.TWO_PAIR + pairList.get(n - 1).getCard();
		} else if (n == 5) {
			result = PorkerConstant.FULL_HOUSE + pairList.get(n - 1).getCard();
		} else if (n == 6 && (pairList.get(0).getNumber() == pairList.get(3).getNumber())
				|| (pairList.get(2).getNumber() == pairList.get(5).getNumber())) {// 포카드+
			// 원페어
			// -끝
			if (pairList.get(0).getNumber() == pairList.get(3).getNumber()) {
				result = PorkerConstant.FOUR_CARD + pairList.get(3).getCard();
			} else {
				result = PorkerConstant.FOUR_CARD + pairList.get(5).getCard();
			}

		} else if (n == 6 && pairList.get(2).getNumber() != pairList.get(3).getNumber()) { // 트리플+
			// 트리플
			// -끝
			result = PorkerConstant.TRIPLE + pairList.get(n - 1).getCard();
		} else if (n == 6 && pairList.get(1).getNumber() != pairList.get(2).getNumber()
				&& pairList.get(3).getNumber() != pairList.get(4).getNumber()) {// 투페어+
			// 원페어
			result = PorkerConstant.TWO_PAIR + pairList.get(n - 1).getCard();
		} else if (n == 7 && pairList.get(0).getNumber() == pairList.get(3).getNumber()
				|| pairList.get(3).getNumber() == pairList.get(6).getNumber()) {
			if (pairList.get(0).getNumber() == pairList.get(3).getNumber()) {
				result = PorkerConstant.FOUR_CARD + pairList.get(3).getCard();
			} else {
				result = PorkerConstant.FOUR_CARD + pairList.get(6).getCard();
			}
		} else if (n == 7 && pairList.get(0).getNumber() == pairList.get(2).getNumber()
				|| pairList.get(2).getNumber() != pairList.get(4).getNumber()
				|| pairList.get(4).getNumber() != pairList.get(6).getNumber()) {
			result = PorkerConstant.FULL_HOUSE + pairList.get(n - 1).getCard();
		}

		return result;
	}

	private void setCharList(ArrayList<CardOne> cards) {
		// 1-> "♣", 2-> "♡",3-> "◇",4-> "♠" 확인
		for (int i = 1; i <= 4; i++) {
			int charCount = 0;
			ArrayList<CardOne> temp = new ArrayList<>();
			for (int j = 0; j < cards.size(); j++) {
				if (i == cards.get(j).getShape()) {
					charCount++;
					temp.add(cards.get(j));
				}
			}
			if (charCount >= 5) {
				flushList.addAll(temp);
			}
		}
	}

}
