package entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> stack;

    public Player( String name ) {
        this.name = name;
        this.stack = new ArrayList();
    }
    
    public boolean addCard( Card card ){
        return stack.add( card );
    }
    
    public boolean removeCard( Card card ){
        return false;
    }

    public List<Card> getStack() {
        return stack;
    }

}
