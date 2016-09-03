package utility;

import entity.Card;
import entity.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Builder {

    public List<Card> getStandard52CardDeck() {
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
            "Queen", "King", "Ace" };
        String[] colors = { "Clubs", "Diamonds", "Hearts", "Spades" };

        List<Card> deck = new ArrayList();

        for ( String rank : ranks ) {
            for ( String color : colors ) {
                deck.add( new Card( rank, color ) );
            }
        }

        return deck;
    }

    /*
     * Shuffle the cards properly... I know you cannot do it in real life! :D
     */
    public void shuffle( List<Card> lc ) {
        long seed = System.nanoTime();
        Collections.shuffle( lc, new Random( seed ) );
        Collections.shuffle( lc, new Random( seed ) );
        Collections.shuffle( lc, new Random( seed ) );
    }

    public List<Integer> getPlayerDeckByCardsIds() {
        List<Integer> cardIds = new ArrayList();
        ArrayList<Integer> fullIdList = new ArrayList();
        for ( int i = 0; i < 51; i++ ) {
            fullIdList.add( i );
        }

        Collections.shuffle( fullIdList );
        for ( int i = 0; i < 26; i++ ) {
            cardIds.add( fullIdList.get( i ) );
        }
        return cardIds;
    }

    public void getPlayersDecks( List<Card> cards, List<Integer> aCards,
            Player a, Player b ) {

        for ( int i = 0; i < cards.size(); i++ ) {
            if ( aCards.contains( i ) ) {
                a.addCard( cards.get( i ) );
            } else {
                b.addCard( cards.get( i ) );
            }
        }
    }
}
