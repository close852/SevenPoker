package com.chu.card;

import java.util.ArrayList;

import com.chu.player.Player;

public class CardCompare {

	public ArrayList<Player> compare(ArrayList<Player> plist) {
		int result = 0;
		ArrayList<Player> list = (ArrayList<Player>) plist.clone();
		ArrayList<Player> temp = new ArrayList<>();
		CardRule rule = new CardRule();
		Player p = new Player();
		int n = 0;
		while (true) {
			n = list.size();
			for (int i = 0; i < n; i++) {
				if (i + 1 < n) {
					int one = rule.cardProcess(list.get(i).getOpenList());
					int two = rule.cardProcess(list.get(i + 1).getOpenList());
					p = one > two ? list.get(i) : list.get(i + 1);
				}
			}
			if (n == 1) {
				p = list.remove(0);
			}
			list.remove(p);
			temp.add(p);
			if (list.size() == 0) {
				break;
			}
		}
		return temp;
	}
}