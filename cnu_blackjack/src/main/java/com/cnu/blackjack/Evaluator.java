package com.cnu.blackjack;

import java.util.Map;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() { // 플레이어 설정 및 초기 카드 설정.
        int dealerScore = dealer.getDealerScore();

        System.out.println("\n------딜러의 스코어는 "+dealerScore+" 점입니다.------");

        for (String PlayerName : playerMap.keySet()) {
            int playerScore = playerMap.get(PlayerName).getHand().getTotalScore();

            System.out.println(">>"+PlayerName+"의 Score는 "+playerScore+"입니다.");

            if (playerScore == 21){
                System.out.println(">>블랙잭! 승리했습니다.");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance()+(playerMap.get(PlayerName).getCurrentBet())*2);
                System.out.println(">>플레이어의 money는 이제 "+playerMap.get(PlayerName).getBalance()+"입니다.\n");
                // 승패 후 처리 추가할것.
            }
            else if (playerScore > 21){
                System.out.println(">>21점을 초과하였으므로 패배하였습니다. (버스트)");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance());
                System.out.println(">>플레이어의 money는 이제 "+playerMap.get(PlayerName).getBalance()+"입니다.\n");
                // 승패 후 처리 추가할것.
            }
            else if ((playerScore < dealerScore) && (dealerScore <= 21)){
                System.out.println(">>딜러보다 낮은 점수이므로 패배하였습니다.");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance());
                System.out.println(">>플레이어의 money는 이제 "+playerMap.get(PlayerName).getBalance()+"입니다.\n");
                // 승패 후 처리 추가할것.
            }
            else if (playerScore > dealerScore) {
                System.out.println(">>딜러보다 높은 점수이므로 승리하였습니다.");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance()+(playerMap.get(PlayerName).getCurrentBet())*2);
                System.out.println(">>플레이어의 money는 이제 "+playerMap.get(PlayerName).getBalance()+"입니다.\n");
            }
            else if (playerScore == dealerScore) {
                System.out.println(">>딜러와 같은 점수이므로 비겼습니다.");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance() + (playerMap.get(PlayerName).getCurrentBet()));
                System.out.println(">>플레이어의 money는 이제 " + playerMap.get(PlayerName).getBalance() + "입니다.\n");
            }
            else if(dealerScore > 21 && playerScore < 21){
                System.out.println(">>딜러가 버스트 되었으므로 승리하였습니다.");
                playerMap.get(PlayerName).setBalance(playerMap.get(PlayerName).getBalance()+(playerMap.get(PlayerName).getCurrentBet())*2);
                System.out.println(">>플레이어의 money는 이제 "+playerMap.get(PlayerName).getBalance()+"입니다.\n");
            }
        }
    }

    private void dealCardToPlayers() { // 카드를 player 갯수 만큼의 map에 카드를 준다.
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
