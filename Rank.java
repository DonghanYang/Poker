/*
An enum class represents card ranks in ascending order.
Each enum constant is declared with corresponding value matches
its command line output.
These values are passed to the constructor when the Ranks are constructed.
*/

public enum Rank {

    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"),
    TEN("10"), JACK("Jack"), QUEEN("Queen"),
    KING("King"), ACE("Ace");

    public final String rank;
    Rank (String rank) { this.rank = rank; }
}