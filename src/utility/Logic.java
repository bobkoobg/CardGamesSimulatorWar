package utility;

import entity.Card;
import java.util.HashMap;
import java.util.Map;

public class Logic {

    String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack",
        "Queen", "King", "Ace" };
    Map<Integer, String> powermode = new HashMap();

    public Logic() {
        for ( int i = 0; i < ranks.length; i++ ) {
            powermode.put( i, ranks[ i ] );
        }
    }

    public int showoff( Card cA, Card cB ) {
        int aPower = 0, bPower = 0;
        for ( Map.Entry<Integer, String> entrySet : powermode.entrySet() ) {

            Integer key = entrySet.getKey();
            String value = entrySet.getValue();

            if ( cA.getRank().equals( value ) ) {
                aPower = key;
            }
            if ( cB.getRank().equals( value ) ) {
                bPower = key;
            }
        }

        if ( aPower > bPower ) {
            return -1;
        } else if ( aPower < bPower ) {
            return 1;
        } else {
            return 0;
        }

    }
}
