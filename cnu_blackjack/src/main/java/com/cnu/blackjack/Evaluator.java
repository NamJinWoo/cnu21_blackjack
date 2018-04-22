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

    }

    private void dealCardToPlayers() { // 카드를 player 갯수 만큼의 map에 카드를 준다.
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
