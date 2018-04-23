package com.cnu.blackjack;

import java.util.concurrent.ThreadLocalRandom;

public class Dealer {

    public int getDealerScore() { // 17 ~ 24 사이의 수를 랜덤으로 해서 나온 수를 딜러의 점수로 포함.
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int score = random.nextInt(17, 25);
        //System.out.println(score);
        return score;
    }
}
