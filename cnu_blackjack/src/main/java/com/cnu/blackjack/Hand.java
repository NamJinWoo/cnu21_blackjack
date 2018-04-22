package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private Deck deck;
    private List<Card> cardList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() { // 카드 한장 받기.
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public List<Card> getCardList() {
        return cardList;
    } // 현재 가지고 있는 카드 반환.


    public int getTotalScore() { // 추가로 구현해주었음.
        int totalScore = 0;
        for (int i = 0; i < cardList.size(); i++) {
            totalScore = totalScore + cardList.get(i).getRank();
        }
        return totalScore;
    }
}
