package logic;

import entity.Card;
import entity.Player;
import java.util.List;
import utility.Builder;

public class Starter {
    
    public static void main( String[] args ) {
        
        Builder builder = new Builder();
        List<Card> lc = builder.getStandard52CardDeck();
        builder.shuffle( lc );
        
        List<Integer> cardsA = builder.getPlayerDeckByCardsIds();
        
        Player a = new Player( "A" );
        Player b = new Player( "B" );
        
        builder.getPlayersDecks( lc, cardsA, a, b );
        
        for ( int i = 0; i < a.getStack().size(); i++ ) {
            System.out.println( "A # " + i + " : " + a.getStack().get( i ).toString() );
        }
        for ( int i = 0; i < b.getStack().size(); i++ ) {
            System.out.println( "B # " + i + " : " + b.getStack().get( i ).toString() );
        }
    }
    
}
