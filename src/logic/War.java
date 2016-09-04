package logic;

import entity.Card;
import entity.Player;
import java.util.ArrayList;
import utility.Utils;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class War {

    private Player a, b;
    private Utils utilities;
    StringBuilder sb = new StringBuilder();
    Random rn = new Random();

    String func = "automatic";

    public static void main( String[] args ) {
        new War().preparation( args );
    }

    private void preparation( String[] args ) {
        if ( args.length > 0 ) {
            if ( args[ 0 ].equals( "manual" ) ) {
                func = args[ 0 ];
            }
        }
        System.out.println( "func is : " + func );

        utilities = new Utils();
        List<Player> p = utilities.getPlayersWithDecks( "A", "B" );
        a = p.get( 0 );
        b = p.get( 1 );

        gameplay();
    }

    private void gameplay() {
        Scanner scanner = new Scanner( System.in );

        int round = 1, result, warCount = 0, stackASize, stackBSize;
        boolean isPlaying = true, isWar = false;

        Card cardA, cardB;
        List<Card> cardsAInWar, cardsBInWar;

        while ( isPlaying ) {
            utilities.sleeper( 1500 );
            utilities.displayCards( a.getStack(), b.getStack() );

            /*
             * Take it slow, darling, take it slow
             */
            if ( func.equals( "automatic" ) ) {
                utilities.sleeper( 2000 );
            } else {
                System.out.println( "Press \"ENTER\" to continue..." );
                scanner.nextLine();
            }
            cardA = a.getStack().get( 0 );
            a.removeCard( cardA );
            cardB = b.getStack().get( 0 );
            b.removeCard( cardB );

            result = utilities.showoff( cardA, cardB );

            sb.append( "\n* Round " )
                    .append( round )
                    .append( " - \n\t(A) " )
                    .append( cardA.getAbbreviation() )
                    .append( " - " )
                    .append( cardA.toString() )
                    .append( " \n\tvs \n\t(B) " )
                    .append( cardB.getAbbreviation() )
                    .append( " - " )
                    .append( cardB.toString() )
                    .append( " => \n\t\tWinner is : " )
                    .append( result == -1
                                    ? "A with " + cardA.getAbbreviation()
                                    : result == 1
                                            ? "B with " + cardB.getAbbreviation()
                                            : "NOBODY ... WAR!" );

            System.out.println( sb.toString() );
            sb.setLength( 0 );

            utilities.sleeper( 1000 );
            if ( result == -1 ) {
                theWinnerTakesItAll( a, cardA, cardB );
            } else if ( result == 1 ) {
                theWinnerTakesItAll( b, cardA, cardB );
            } else {
                isWar = true;
                cardsAInWar = new ArrayList();
                cardsBInWar = new ArrayList();
                while ( isWar ) {
                    warCount++;
                    utilities.sleeper( 1000 );

                    stackASize = a.getStack().size();
                    stackBSize = b.getStack().size();
                    for ( int x = 0; x < 2; x++ ) {
                        for ( int y = 0; y < 3; y++ ) {
                            if ( x == 0 ) {
                                if ( stackASize > 0 ) {
                                    cardsAInWar.add( a.getStack().get( 0 ) );
                                    sb.append( "A - " )
                                            .append( (y + 1) )
                                            .append( " =>" )
                                            .append( a.getStack().get( 0 ).getAbbreviation() )
                                            .append( " ( " )
                                            .append( a.getStack().get( 0 ).toString() )
                                            .append( " )" );
                                    System.out.println( sb.toString() );
                                    sb.setLength( 0 );

                                    a.removeCard( a.getStack().get( 0 ) );
                                    stackASize--;

                                } else {
                                    Card enemyCard = utilities.getRandomCardFromEnemyDeck( b.getStack() );

                                    cardsAInWar.add( enemyCard );
                                    sb.append( "A (takes a card from B) - " )
                                            .append( (y + 1) )
                                            .append( " =>" )
                                            .append( enemyCard.getAbbreviation() )
                                            .append( " ( " )
                                            .append( enemyCard.toString() )
                                            .append( " )" );
                                    System.out.println( sb.toString() );
                                    sb.setLength( 0 );

                                    b.removeCard( enemyCard );
                                }
                            } else {
                                if ( stackBSize > 0 ) {
                                    cardsBInWar.add( b.getStack().get( 0 ) );
                                    sb.append( "B - " )
                                            .append( (y + 1) )
                                            .append( " =>" )
                                            .append( b.getStack().get( 0 ).getAbbreviation() )
                                            .append( " ( " )
                                            .append( b.getStack().get( 0 ).toString() )
                                            .append( " )" );
                                    System.out.println( sb.toString() );
                                    sb.setLength( 0 );

                                    b.removeCard( b.getStack().get( 0 ) );
                                    stackBSize--;
                                } else {
                                    Card enemyCard = utilities.getRandomCardFromEnemyDeck( a.getStack() );

                                    cardsBInWar.add( enemyCard );
                                    sb.append( "B (takes a card from A) - " )
                                            .append( (y + 1) )
                                            .append( " =>" )
                                            .append( enemyCard.getAbbreviation() )
                                            .append( " ( " )
                                            .append( enemyCard.toString() )
                                            .append( " )" );
                                    System.out.println( sb.toString() );
                                    sb.setLength( 0 );

                                    a.removeCard( enemyCard );
                                }
                            }
                            utilities.sleeper( 2000 );
                        }
                    }

                    result = utilities.showoff( cardsAInWar.get( (3 * warCount) - 1 ), cardsBInWar.get( (3 * warCount) - 1 ) );

                    sb.append( "* War result : \n\tA's 3rd card is : " )
                            .append( cardsAInWar.get( (3 * warCount) - 1 ) )
                            .append( "\n\tB's 3rd card is : " )
                            .append( cardsBInWar.get( (3 * warCount) - 1 ) )
                            .append( " => \n\t\tWinner is : " )
                            .append( result == -1
                                            ? "A with " + cardsAInWar.get( (3 * warCount) - 1 ).getAbbreviation()
                                            : result == 1
                                                    ? "B with " + cardsBInWar.get( (3 * warCount) - 1 ).getAbbreviation()
                                                    : "NOBODY ... WAR!" );
                    System.out.println( sb.toString() );
                    sb.setLength( 0 );

                    if ( result == -1 ) {
                        for ( int i = 0; i < cardsAInWar.size(); i++ ) {
                            a.addCard( cardsAInWar.get( i ) );
                            a.addCard( cardsBInWar.get( i ) );
                        }
                        theWinnerTakesItAll( a, cardA, cardB );
                        isWar = false;
                        warCount = 0;
                    } else if ( result == 1 ) {
                        for ( int i = 0; i < cardsAInWar.size(); i++ ) {
                            b.addCard( cardsAInWar.get( i ) );
                            b.addCard( cardsBInWar.get( i ) );
                        }
                        theWinnerTakesItAll( b, cardA, cardB );
                        isWar = false;
                        warCount = 0;
                    }
                }
            }

            if ( a.getStack().isEmpty() || b.getStack().isEmpty() ) {
                isPlaying = false;
            }
            round++;
        }
        System.out.println( "Final results :" );
        utilities.displayCards( a.getStack(), b.getStack() );
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
