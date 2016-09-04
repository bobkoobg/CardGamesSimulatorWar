package utility;

import entity.Card;
import entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.War;

public class Utils {

    StringBuilder sb;

    public Utils() {
        sb = new StringBuilder();
    }

    /*
     * This method returns a list of 52 cards based on the following links :
     * https://en.wikipedia.org/wiki/Standard_52-card_deck
     * https://en.wikipedia.org/wiki/French_playing_cards
     * https://bg.wikipedia.org/wiki/%D0%92%D0%BE%D0%B9%D0%BD%D0%B0_(%D0%B8%D0%B3%D1%80%D0%B0)
     * https://bg.wikipedia.org/wiki/%D0%91%D1%80%D0%B8%D0%B4%D0%B6-%D0%B1%D0%B5%D0%BB%D0%BE%D1%82
     * they will be used as an essential component of the "War" cards game.
     */
    private List<Card> getStandard52CardsDeck() {
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
            "Queen", "King", "Ace" };
        String[] colors = { "Spades", "Hearts", "Clubs", "Diamonds" };
        String[] abbreviations = { "🂢", "🂲", "🃒", "🃂", "🂣", "🂳", "🃓", "🃃",
            "🂤", "🂴", "🃔", "🃄", "🂥", "🂵", "🃕", "🃅", "🂦", "🂶", "🃖", "🃆",
            "🂧", "🂷", "🃗", "🃇", "🂨", "🂸", "🃘", "🃈", "🂩", "🂹", "🃙", "🃉",
            "🂪", "🂺", "🃚", "🃊", "🂫", "🂻", "🃛", "🃋", "🂭", "🂽", "🃝", "🃍",
            "🂮", "🂾", "🃞", "🃎", "🂡", "🂱", "🃑", "🃁" };

        List<Card> deck = new ArrayList();

        int z = 0;
        for ( int x = 0; x < ranks.length; x++ ) {
            for ( int y = 0; y < colors.length; y++ ) {
                deck.add( new Card( ranks[ x ], colors[ y ], x, abbreviations[ z ] ) );
                z++;
            }
        }

        return deck;
    }

    /*
     * Shuffle the cards properly... I know you cannot do it in real life! :D
     */
    private void shuffle( List<Card> lc ) {
        long seed = System.nanoTime();
        Collections.shuffle( lc, new Random( seed ) );
        Collections.shuffle( lc, new Random( seed ) );
        Collections.shuffle( lc, new Random( seed ) );
    }

    /*
     * This method returns 24 unique number from 0 to 51. They will be used to 
     * randomly select 24 unique cards from the 52 cards deck.
     */
    private List<Integer> get24UniqueCardIds() {
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

    /*
     * This method returns list of 2 player objects with randomized cards from
     * a standard 52 cards deck
     * Actions : 
     * 1. Get the standard 52 Cards deck.
     * 2. Shuffle the deck 2 times (increase randomness).
     * 3. Choose unique 24 numbers (from 0-51), which correspond to the ids of
     *      List<Card> cards.
     * 4. Loop through the List<Card> and add a card to Player A if the chosen 
     *      randomized number from the previous action matches the current loop id.
     * 5. Add the newly created Player objects to a list and return the list.
     */
    public List<Player> getPlayersWithDecks( String aName, String bName ) {
        Player a = new Player( "A" );
        Player b = new Player( "B" );

        List<Card> cards = getStandard52CardsDeck();
        shuffle( cards );

        List<Integer> uc = get24UniqueCardIds();
        for ( int i = 0; i < cards.size(); i++ ) {
            if ( uc.contains( i ) ) {
                a.addCard( cards.get( i ) );
            } else {
                b.addCard( cards.get( i ) );
            }
        }
        return new ArrayList( Arrays.asList( a, b ) );
    }

    public int showoff( Card aCard, Card bCard ) {
        if ( aCard.getStrength() > bCard.getStrength() ) {
            return -1;
        } else if ( aCard.getStrength() < bCard.getStrength() ) {
            return 1;
        } else {
            return 0;
        }
    }

    public void displayCards( List<Card> aStack, List<Card> bStack ) {
        int aStackSize = aStack.size();
        int bStackSize = bStack.size();
        int size = aStackSize >= bStackSize ? aStackSize : bStackSize;

        sb.append( "* Status : A (" )
                .append( aStack.size() )
                .append( " cards ) vs B (" )
                .append( bStack.size() )
                .append( " cards )\n* Loading cards..." );

        System.out.println( sb.toString() );
        sb.setLength( 0 );

        sleeper( 1500 );

        for ( int i = 0; i < size; i++ ) {

            if ( i < aStackSize ) {
                sb.append( (i + 1) )
                        .append( "." )
                        .append( ((i + 1) < 10 ? " " : "") )
                        .append( aStack.get( i ).getAbbreviation() )
                        .append( " - " )
                        .append( aStack.get( i ).toString() )
                        .append( "\t\t" );
            } else {
                sb.append( "\t\t\t\t" );
            }
            if ( i < bStackSize ) {
                sb.append( (i + 1) )
                        .append( "." )
                        .append( ((i + 1) < 10 ? " " : "") )
                        .append( bStack.get( i ).getAbbreviation() )
                        .append( " - " )
                        .append( bStack.get( i ).toString() )
                        .append( "\n" );
            } else {
                sb.append( "\n" );
            }

        }
        System.out.println( sb.toString() );
        sb.setLength( 0 );
    }

    public Card getRandomCardFromEnemyDeck( List<Card> enemyCards ) {
        Random rn = new Random();
        int cardId = rn.nextInt( enemyCards.size() );
        return enemyCards.get( cardId );
    }

    public void sleeper( long millis ) {
        try {
            Thread.sleep( millis );
        } catch ( InterruptedException ex ) {
            Logger.getLogger( War.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
}
