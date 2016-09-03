package logic;

import entity.Card;
import entity.Player;
import utility.Builder;
import utility.Logic;
import java.util.List;

public class Starter {

    private Player a, b;
    private Builder builder;
    private Logic logic;

    public static void main( String[] args ) {
        new Starter().preparation();
    }

    private void preparation() {
        builder = new Builder();
        List<Card> lc = builder.getStandard52CardDeck();
        builder.shuffle( lc );

        a = new Player( "A" );
        b = new Player( "B" );

        builder.getPlayersDecks( lc, a, b );
        for ( int i = 0; i < a.getStack().size(); i++ ) {
            System.out.println( "A : " + a.getStack().get( i ) );
            System.out.println( "B : " + b.getStack().get( i ) );
        }
        gameplay();
    }

    private void gameplay() {
        logic = new Logic();
        int round = 1;
        int result;
        Card ca, cb, caw1, caw2, caw3, cbw1, cbw2, cbw3;
        boolean isPlaying = true;
        while ( isPlaying ) {

            ca = a.getStack().get( 0 );
            cb = b.getStack().get( 0 );
            result = logic.showoff( ca, cb );
            System.out.println( "* Round " + round + " - (A) " + a.getStack().get( 0 ).getRank()
                    + " vs (B) " + b.getStack().get( 0 ).getRank() + ", the winner is : " + (result == -1 ? "A" : "B") );
            a.removeCard( ca );
            b.removeCard( cb );

            if ( result == -1 ) {
                a.addCard( ca );
                a.addCard( cb );
            } else if ( result == 1 ) {
                b.addCard( ca );
                b.addCard( cb );
            } else {
                System.out.println( "WAR!!!!!!!!!" );
                if ( a.getStack().size() < 3 || b.getStack().size() < 3 ) {
                    break;
                }
                caw1 = a.getStack().get( 0 );
                a.removeCard( caw1 );
                caw2 = a.getStack().get( 0 );
                a.removeCard( caw2 );
                caw3 = a.getStack().get( 0 );
                a.removeCard( caw3 );

                cbw1 = b.getStack().get( 0 );
                b.removeCard( cbw1 );
                cbw2 = b.getStack().get( 0 );
                b.removeCard( cbw2 );
                cbw3 = b.getStack().get( 0 );
                b.removeCard( cbw3 );

                System.out.println( "A : 1 " + caw1.toString() + ", 2 "
                        + caw2.toString() + ", 3 " + caw3.toString() );
                System.out.println( "B : 1 " + cbw1.toString() + ", 2 "
                        + cbw2.toString() + ", 3 " + cbw3.toString() );

                result = logic.showoff( caw3, cbw3 );
                System.out.println( "War result is ? : " + result );
                if ( result == -1 ) {
                    a.addCard( ca );
                    a.addCard( cb );
                    a.addCard( caw1 );
                    a.addCard( caw2 );
                    a.addCard( caw3 );
                    a.addCard( cbw1 );
                    a.addCard( cbw2 );
                    a.addCard( cbw3 );
                } else if ( result == 1 ) {
                    b.addCard( ca );
                    b.addCard( cb );
                    b.addCard( caw1 );
                    b.addCard( caw2 );
                    b.addCard( caw3 );
                    b.addCard( cbw1 );
                    b.addCard( cbw2 );
                    b.addCard( cbw3 );
                }

            }

            if ( a.getStack().isEmpty() || b.getStack().isEmpty() ) {
                isPlaying = false;
            }
            round++;

            if ( round > 10000 ) {
                isPlaying = false;
            }
            System.out.println( "* Round " + round + " - (A stack size) " + a.getStack().size() + " vs (B stack size) " + b.getStack().size() );
            System.out.println( "##############################" );

        }
    }

}
