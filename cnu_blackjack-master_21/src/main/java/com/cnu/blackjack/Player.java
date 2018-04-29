package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NotEnoughBalanceException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

@Data
public class Player {

    private int balance;
    private int currentBet;
    private Hand hand;

    private Boolean isStay;

    public Player(int seedMoney, Hand hand) { // 현재 가지고 있는 금액과 Hand.
        this.balance = seedMoney;
        this.hand = hand;
        this.isStay = false;
    }

    public void placeBet(int bet) { // 플레이어가 배팅하는 함수.
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }
        balance -= bet;
        currentBet = bet;
    }

    public Card hitCard() {
        return hand.drawCard();
    }
}
