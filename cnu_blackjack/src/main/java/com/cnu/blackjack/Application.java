package com.cnu.blackjack;

import com.sun.xml.internal.ws.message.PayloadElementSniffer;
import javafx.scene.input.KeyCode;

import java.util.Scanner;

public class Application {

    Scanner sc = new Scanner(System.in); // 사용자로부터 입력받기.
    public int stayedPlayer = 0;

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in); // 사용자로부터 입력받기.
        int playerNumber;
        String playerName;
        int playerSeed;
        int bettingMoney; // 플레이어가 배팅할 금액.

        System.out.println("<<Game Start>>");
        Deck deck = new Deck(1);
        Game game = new Game(deck);

        /*System.out.printf("플레이어의 수를 입력하세요 : ");
        playerNumber = sc.nextInt();

        for (int i = 1; i <= playerNumber; i++) {
            System.out.printf(i + "번 플레이어의 정보를 입력합니다.");
            System.out.printf("플레이어의 이름을 입력하세요 : ");
            playerName = sc.next();
            System.out.printf("플레이어의 seedMoney 입력하세요 : ");
            playerSeed = sc.nextInt();
            game.addPlayer(playerName, playerSeed);
        }*/
        game.addPlayer("kim", 3000);
        game.addPlayer("nam", 4000);

        System.out.println("현재 플레이어의 수 : " + game.getPlayerList().size() + "\n");

        Evaluator ev = new Evaluator(game.getPlayerList()); // evaluator 객체 생성, 카드 두장씩 받음.

        for (String PlayerName : game.getPlayerList().keySet()) {
            System.out.printf("배팅 금액을 입력하세요 : ");
            bettingMoney = sc.nextInt();
            game.getPlayerList().get(PlayerName).placeBet(bettingMoney);
        }

        for (String PlayerName : game.getPlayerList().keySet()) {
            System.out.println("플레이어 " + PlayerName + "가 가지고 있는 카드수는 " + game.getPlayerList().
                    get(PlayerName).getHand().getCardList().size() + "  seedMoney : " + game.getPlayerList().
                    get(PlayerName).getBalance());
            for (int i = 0; i < game.getPlayerList().get(PlayerName).getHand().getCardList().size(); i++) {
                System.out.println(game.getPlayerList().get(PlayerName).getHand().getCardList().get(i));
            }
        }

        /*플레이어끼리 돌아가면서 플레이 시작.*/

        for (String PlayerName : game.getPlayerList().keySet()) {
            System.out.println("----------------------------------------");
            int total = game.getPlayerList().get(PlayerName).getHand().getTotalScore();

            if (game.getPlayerList().get(PlayerName).getIsStay() == false) {
                //while (game.getPlayerList().get(PlayerName).getIsStay()== true) {
                System.out.println("플레이어 " + PlayerName + " 의 차례입니다. 현재 남은 금액은 " +
                        game.getPlayerList().get(PlayerName).getBalance() + "원 입니다.");
                System.out.println("현재 스코어는 " + total + " 점 입니다.");


                while (total < 16) { // 토탈이 16이하면 자동 히트.
                    System.out.println("토탈 점수가 16 이하이므로 히트합니다. ");
                    game.getPlayerList().get(PlayerName).hitCard();
                    total = game.getPlayerList().get(PlayerName).getHand().getTotalScore();

                    for (int i = 0; i < game.getPlayerList().get(PlayerName).getHand().getCardList().size(); i++) {
                        System.out.println(game.getPlayerList().get(PlayerName).getHand().getCardList().get(i));
                    } // 카드 정보 출력
                }

                if (total >= 16 && total <= 21) {
                    System.out.println(" (현재점수: "
                            + game.getPlayerList().get(PlayerName).getHand().getTotalScore() + " ) . Stay하고  턴을 종료합니다.");
                    game.getPlayerList().get(PlayerName).setIsStay(true); // 턴이 종료되었으므로 true로 바꾼다
                } else {
                    System.out.println("토탈 점수가 21점이 넘었으므로 버스트 (현재점수: "
                            + game.getPlayerList().get(PlayerName).getHand().getTotalScore() + " ) 턴을 종료합니다.");
                    game.getPlayerList().get(PlayerName).setIsStay(true); // 턴이 종료되었으므로 true로 바꾼다
                }

                // }
            }
        }

        //game.start();
    }
}

