package entity;

public class Card {

    private final String rank;
    private final String color;
    private final int strength;
    private final String abbreviation;

    public Card( String rank, String color, int strength, String abbreviation ) {
        this.rank = rank;
        this.color = color;
        this.strength = strength;
        this.abbreviation = abbreviation;
    }

    public String getRank() {
        return rank;
    }

    public int getStrength() {
        return strength;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return rank + " of " + color;
    }

    public String toString2() {
        return "(" + abbreviation + ")" + rank + " of " + color + "(" + strength + ")";
    }

}
