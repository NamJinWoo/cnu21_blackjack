package com.cnu.blackjack;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvaluatorTest {

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Deck deck = new Deck(1);
        Game game = new Game(deck);
        game.addPlayer("test1", 5000);
        game.addPlayer("test2", 3000);
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        for (String PlayerName : game.getPlayerList().keySet()){
            assertThat(game.getPlayerList().get(PlayerName).getHand().getCardList().size(), is(2));
        }
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {

    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {

    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {

    }
}
