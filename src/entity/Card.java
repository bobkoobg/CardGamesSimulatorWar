package entity;

public class Card {

    private String rank;
    private String color;

    public Card( String rank, String color ) {
        this.rank = rank;
        this.color = color;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + color;
    }

}
