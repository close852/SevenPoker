package com.chu.player;

import java.util.ArrayList;

import com.chu.card.CardOne;

public class Player {

	private String name;
	private int money;
	private ArrayList<CardOne> myList;
	private ArrayList<CardOne> openList;
	private ArrayList<String> printList;

	public Player() {
		money = 10000000;
		myList = new ArrayList<>();
		openList = new ArrayList<>();
		printList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPrintList() {
		for (int i = 0, n = myList.size(); i < n; i++) {
			if (i == 0 || i == 1 || i == n - 1) {
				printList.set(i, "?");
			} else {
				printList.set(i, myList.get(i).toString());
			}
		}
		return printList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<CardOne> getMyList() {
		return myList;
	}

	public void setMyList(ArrayList<CardOne> myList) {
		this.myList.addAll(myList);
	}

	public void setMyList(CardOne cardOne) {
		this.myList.add(cardOne);
	}

	public ArrayList<CardOne> getOpenList() {
		return openList;
	}

	public void setOpenList(ArrayList<CardOne> openList) {
		this.openList.addAll(openList);
	}

	public void setOpenList(CardOne cardOne) {
		openList.add(cardOne);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + money;
		result = prime * result + ((myList == null) ? 0 : myList.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((openList == null) ? 0 : openList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (money != other.money)
			return false;
		if (myList == null) {
			if (other.myList != null)
				return false;
		} else if (!myList.equals(other.myList))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openList == null) {
			if (other.openList != null)
				return false;
		} else if (!openList.equals(other.openList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", money=" + money + ", myList=" + myList + ", openList=" + openList + "]";
	}

}