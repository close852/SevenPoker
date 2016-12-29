package com.chu.batting;

import com.chu.player.Player;
import com.chu.player.controller.PlayerController;

public class BattingController {
	int check = 0;
	Batting bt = new Batting();
	int size = PlayerController.playerNum;

	// 생성자를 통해 인원수 만큼의 초기 돈을 총 금액에 저장함
	public BattingController() {
		bt.setFirstSumBatting(50 * size);
		bt.setSumBatting(bt.getFirstSumBatting());
	}

	// 각각의 player마다
	public boolean make(Player p) {
		boolean isc = false;
		if (check == 0) {
			bt.setCurrentSumBatting(0);
			bt.firstBattingProcess();
			p.setMoney(p.getMoney() - bt.getCurrentSumBatting());
			if (p.getMoney() <= 0) {
				bt.setCurrentSumBatting(bt.getCurrentSumBatting() + p.getMoney());
				bt.setSumBatting(bt.getSumBatting() + p.getMoney());
				p.setMoney(0);
				System.out.println("올인" + p.getMoney());
			} else {
				System.out.println(p.getMoney());
			}
			check++;
		}

		else {
			bt.beforeBattingProcess();
			p.setMoney(p.getMoney() - bt.getCurrentSumBatting());
			if (p.getMoney() < 0) {
				bt.setCurrentSumBatting(bt.getCurrentSumBatting() + p.getMoney());
				bt.setSumBatting(bt.getSumBatting() + p.getMoney());
				p.setMoney(0);
				System.out.println("올인" + p.getMoney());
			}

			else {
				System.out.println(p.getMoney());
			}

			check++;
			if (check == size) {
				check = 0;
			}
		}

		// 플레이어가 죽었는지를 확인
		if (bt.isDieCheck() == true) {
			isc = true;
			bt.setDieCheck(false);
		}
		return isc;
	}
}
