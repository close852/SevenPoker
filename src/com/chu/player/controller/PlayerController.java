package com.chu.player.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;

import com.chu.common.InputNum;
import com.chu.player.Player;

public class PlayerController {
 
   public static int playerNum;
   public static ArrayList<Player> createList() throws InputMismatchException {

      System.out.println("유저 몇명 만들래");
      int num = InputNum.input();
      playerNum = num;
      ArrayList<Player> playerList = new ArrayList<>();
      for (int i = 0; i < num; i++) {
         playerList.add(new Player());
         
      }
      return playerList;
   }
}