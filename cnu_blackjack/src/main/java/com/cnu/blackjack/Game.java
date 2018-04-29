package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.DuplicatePlayerException;
import com.cnu.blackjack.exceptions.NotEveyonePlacedBetException;
import com.cnu.blackjack.exceptions.PlayerDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Map<String, Player> playerList = new HashMap<>();
    private Deck deck;


    public Game(Deck deck) {
        this.deck = deck;
    } // 게임에 사용하는 덱.

    public void addPlayer(String playerName, int seedMoney) { // 플레이어 추가 함수.
        Player player = new Player(seedMoney, new Hand(deck));
        if (playerList.get(playerName) != null) { // Map에 동일한 이름의 플레이어가 존재한다면
            throw new DuplicatePlayerException();
        }
        playerList.put(playerName, player);
    }

    public Map<String, Player> getPlayerList() {
        return playerList;
    } // 현재 플레이어 명단 반환.

    public void start() {
        playerList.forEach((name, player) -> {
            if (player.getCurrentBet() == 0) {
                throw new NotEveyonePlacedBetException();
            }
        });

    }

    public void placeBet(String name, int bet) {
        Player player = playerList.get(name);
        if (player == null) {
            throw new PlayerDoesNotExistException();
        }
        player.placeBet(bet);
    }
}
