package logic;

import entity.Card;
import entity.Player;
import utility.Utils;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class War {

    private Player a, b;
    private Utils utilities;
    StringBuilder sb = new StringBuilder();
    Random rn = new Random();

    public static void main( String[] args ) {
        new War().preparation();
    }

    private void preparation() {
        utilities = new Utils();
        List<Player> p = utilities.getPlayersWithDecks( "A", "B" );
        a = p.get( 0 );
        b = p.get( 1 );
//        for ( int i = 0; i < a.getStack().size(); i++ ) {
//            System.out.println( "A:   " + a.getStack().get( i ).toString2() );
//            System.out.println( "B:   " + b.getStack().get( i ).toString2() );
//        }
        gameplay();
    }

    private void gameplay() {

        int round = 1, result, random;
        Card cardA, cardB, caw1, caw2, caw3, cbw1, cbw2, cbw3;
        boolean isPlaying = true;
        while ( isPlaying ) {
            utilities.displayCards( a.getStack(), b.getStack() );

            /*
             * Take it slow, darling, take it slow
             */
            try {
                Thread.sleep( 100 );
            } catch ( InterruptedException ex ) {
                Logger.getLogger( War.class.getName() ).log( Level.SEVERE, null, ex );
            }

            cardA = a.getStack().get( 0 );
            a.removeCard( cardA );
            cardB = b.getStack().get( 0 );
            b.removeCard( cardB );

            result = utilities.showoff( cardA, cardB );

            sb.append( "##############################\n" )
                    .append( "* Round " )
                    .append( round )
                    .append( " - (A) " )
                    .append( cardA.getAbbreviation() )
                    .append( " - " )
                    .append( cardA.toString() )
                    .append( " vs (B) " )
                    .append( cardB.getAbbreviation() )
                    .append( " - " )
                    .append( cardB.toString() )
                    .append( " => Winner is : " )
                    .append( result == -1
                                    ? "A with " + cardA.getAbbreviation()
                                    : result == 1
                                            ? "B with " + cardB.getAbbreviation()
                                            : "NOBODY ... WAR!" );
            System.out.println( sb.toString() );
            sb.setLength( 0 );

            random = rn.nextInt( 1 );
            if ( result == -1 ) {
                theWinnerTakesItAll( a, cardA, cardB );
            } else if ( result == 1 ) {
                theWinnerTakesItAll( b, cardA, cardB );
            } else {
                try {
                    Thread.sleep( 1000 );
                } catch ( InterruptedException ex ) {
                    Logger.getLogger( War.class.getName() ).log( Level.SEVERE, null, ex );
                }
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

                sb.append( "A : 1 " )
                        .append( caw1.getAbbreviation() )
                        .append( ", 2 " )
                        .append( caw2.getAbbreviation() )
                        .append( ", 3 " )
                        .append( caw3.getAbbreviation() )
                        .append( "B : 1 " )
                        .append( cbw1.getAbbreviation() )
                        .append( ", 2 " )
                        .append( cbw2.getAbbreviation() )
                        .append( ", 3 " )
                        .append( cbw3.getAbbreviation() )
                        .append( "\n" );

                result = utilities.showoff( caw3, cbw3 );

                sb.append( "War result : " ).append( result );
                System.out.println( sb.toString() );
                sb.setLength( 0 );

                if ( result == -1 ) {
                    a.addCard( cardA );
                    a.addCard( cardB );
                    a.addCard( caw1 );
                    a.addCard( caw2 );
                    a.addCard( caw3 );
                    a.addCard( cbw1 );
                    a.addCard( cbw2 );
                    a.addCard( cbw3 );
                } else if ( result == 1 ) {
                    b.addCard( cardA );
                    b.addCard( cardB );
                    b.addCard( caw1 );
                    b.addCard( caw2 );
                    b.addCard( caw3 );
                    b.addCard( cbw1 );
                    b.addCard( cbw2 );
                    b.addCard( cbw3 );
                }
                if ( a.getStack().size() < 3 || b.getStack().size() < 3 ) {
                    break;
                }
            }

            if ( a.getStack().isEmpty() || b.getStack().isEmpty() ) {
                isPlaying = false;
            }
            round++;
        }
    }

    private void theWinnerTakesItAll( Player winner, Card cardA, Card cardB ) {
        int random = rn.nextInt( 2 );
        if ( random == 0 ) {
            winner.addCard( cardA );
            winner.addCard( cardB );
        } else {
            winner.addCard( cardB );
            winner.addCard( cardA );
        }
    }
}
