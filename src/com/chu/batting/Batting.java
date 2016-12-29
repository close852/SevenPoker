package com.chu.batting;

import java.util.Scanner;

public class Batting {
   private int choice = 0;                             // 선택 원하는 배팅을 선택
   private boolean dieCheck = false;               // 죽은 플레이어 확인
    
   public boolean isDieCheck() {
      return dieCheck;
   }
   
   public void setSumBatting(int sumBatting) {
      this.sumBatting = sumBatting;
   }
   
   private int sumBatting = 0;                      // 현재 게임의 
   private int currentSumBatting = 0;                // 현재 돌아가는 판돈 
   private int firstSumBatting = 0;                   // 초기 판돈과 
   
   public int getFirstSumBatting() {
      return firstSumBatting;
   }
   
   public void setFirstSumBatting(int firstSumBatting) {
      this.firstSumBatting = firstSumBatting;
   }
   public int getSumBatting() {
      return sumBatting;
   }
   
   public int getCurrentSumBatting() {
      return currentSumBatting;
   }
   
   public void setDieCheck(boolean dieCheck) {
      this.dieCheck = dieCheck;
   }
   
   // 선이 먼저 시작함.
   public void firstBattingProcess(){                 
         System.out.println("원하는 배팅을 입력하세요.(1.삥 2.하프 3.풀 4. 체크 5. 다이)");
         Scanner sc = new Scanner(System.in);
         choice = sc.nextInt();
         switch(choice){
         case 1:
            bbing();
            break;
         case 2:
            half();
            break;
         case 3:
            full();
            break;
         case 4:
            check();
            break;
         case 5:
            die();
            break;
         }
      }   
   
   // 그다음 부터는 반시계 순서대로 
   public void beforeBattingProcess(){
         System.out.println("원하는 배팅을 입력하세요.(1.콜 2.하프 3.풀 4. 다이)");
         Scanner sc = new Scanner(System.in);
         choice = sc.nextInt();
         switch(choice){
         case 1:
            call();
            break;
         case 2:
            half();
            break;
         case 3:
            full();
            break;
         case 4:
            die();
            break;
         }
      }
   
   // 콜을 할 시 
   public void call(){
      System.out.println("콜");
      sumBatting += currentSumBatting;
      System.out.println("총 배팅"+sumBatting);
   
   }
   
   public void setCurrentSumBatting(int currentSumBatting) {
      this.currentSumBatting = currentSumBatting;
   }
   
   // 현재 총 배팅금액의 절반을 검
   public void half(){
      firstSumBatting = sumBatting + (sumBatting/2) + currentSumBatting;
      currentSumBatting = (currentSumBatting*2)+(sumBatting/2);
      sumBatting = firstSumBatting;
      System.out.println("하프");
      System.out.println("현재 배팅"+currentSumBatting);
      System.out.println("총 배팅"+sumBatting);
   }
   
   // 플레이어 윽엑
   public boolean die(){
      dieCheck = true;
      System.out.println("플레이어 다이");
      return dieCheck;
   }
   
    // 총 배팅금액 만큼의 돈을 추가로 건다.
   public void full(){
      firstSumBatting = sumBatting*2 + currentSumBatting;
      currentSumBatting  += (sumBatting+currentSumBatting);
      sumBatting = firstSumBatting;
      System.out.println("풀");
      System.out.println("현재 배팅"+ currentSumBatting);
      System.out.println("총 배팅"+sumBatting);
   }
   
   // 판돈은 50원 부터 시작 삥은 판돈만 입력
   public void bbing(){
      sumBatting+= 50;
      currentSumBatting +=50;
      System.out.println("삥");
      System.out.println("현재 배팅"+ currentSumBatting);
      System.out.println("총 배팅"+sumBatting);
   }
   
   // 선플레이어만 가능하며 돈을 내지 않고 턴을 넘김 
   public void check(){
      System.out.println("체크");
      System.out.println("총 배팅"+sumBatting);
   }
}